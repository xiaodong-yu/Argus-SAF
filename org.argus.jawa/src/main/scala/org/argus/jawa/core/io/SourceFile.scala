/*
 * Copyright (c) 2017. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jawa.core.io

import java.io.{BufferedReader, StringReader}

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.body.{BodyDeclaration, TypeDeclaration}
import com.github.javaparser.ast.{NodeList, CompilationUnit => JavaCompilationUnit}
import org.argus.jawa.core.Chars._
import org.argus.jawa.core.{Global, JawaType, Reporter}
import org.argus.jawa.core.frontend.MyClass
import org.argus.jawa.core.frontend.javafile.JavaFileParser
import org.argus.jawa.core.frontend.jawafile.JawaFileParser
import org.argus.jawa.core.util._

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

/** abstract base class of a source file used in the compiler */
abstract class SourceFile {
  def content: Array[Char]         // normalized, must end in SU
  def file: AbstractFile
  def isLineBreak(idx: Int): Boolean
  def isEndOfLine(idx: Int): Boolean
  def isSelfContained: Boolean
  def length: Int
  def position(offset: Int): Position = {
    assert(offset < length, file + ": " + offset + " >= " + length)
    Position.offset(this, offset)
  }
  def code: String = new String(content)
  def offsetToLine(offset: Int): Int
  def lineToOffset(index: Int): Int

  /** Map a position to a position in the underlying source file.
   *  For regular source files, simply return the argument.
   */
  def positionInUltimateSource(position: Position): Position = position
  override def toString: String = file.name
  def path: String = file.path

  def lineToString(index: Int): String = {
    val start = lineToOffset(index)
    var end = start
    while (end < length && !isEndOfLine(end)) end += 1
    new String(content, start, end - start)
  }

  @tailrec
  final def skipWhitespace(offset: Int): Int =
    if (content(offset).isWhitespace) skipWhitespace(offset + 1) else offset

  def identifier(pos: Position): Option[String] = None

  def parse(reporter: Reporter): IMap[JawaType, MyClass]
}

/** An object representing a missing source file.
 */
object NoSourceFile extends SourceFile {
  def content                   = Array()
  def file: AbstractFile        = NoFile
  def isLineBreak(idx: Int)     = false
  def isEndOfLine(idx: Int)     = false
  def isSelfContained           = true
  def length: Int = -1
  def offsetToLine(offset: Int): Int = -1
  def lineToOffset(index: Int): Int = -1
  def parse(reporter: Reporter): IMap[JawaType, MyClass] = imapEmpty
  override def toString = "<no source file>"
}

object NoFile extends VirtualFile("<no file>", "<no file>")

class StringFile(code: String) extends  VirtualFile("<String>", "<String>") {
  content = code.getBytes()
}

abstract class DefaultSourceFile(val file: AbstractFile) extends SourceFile {
  def this(sourceName: String)   = this(new VirtualFile(sourceName))

  // If non-whitespace tokens run all the way up to EOF,
  // positions go wrong because the correct end of the last
  // token cannot be used as an index into the char array.
  // The least painful way to address this was to add a
  // newline to the array.
  def content: Array[Char] = file.toCharArray
  def length: Int = content.length
  def start = 0
  def isSelfContained = true
  override def identifier(pos: Position): Option[String] =
    if (pos.isDefined && pos.source == this && pos.point != -1) {
      def isOK(c: Char) = isIdentifierPart(c, isGraveAccent = true) || isOperatorPart(c)
      Some(new String(content drop pos.point takeWhile isOK))
    } else {
      super.identifier(pos)
    }

  private def charAtIsEOL(idx: Int)(p: Char => Boolean) = {
    // don't identify the CR in CR LF as a line break, since LF will do.
    def notCRLF0 = content(idx) != CR || !content.isDefinedAt(idx + 1) || content(idx + 1) != LF

    idx < length && notCRLF0 && p(content(idx))
  }

  def isLineBreak(idx: Int): Boolean =
    if (idx >= length) false else content(idx) match {
      // don't identify the CR in CR LF as a line break, since LF will do.
      case CR => (idx + 1 == length) || (content(idx + 1) != LF)
      case x  => isLineBreakChar(x)
    }

  /** True if the index is included by an EOL sequence. */
  def isEndOfLine(idx: Int): Boolean = (content isDefinedAt idx) && PartialFunction.cond(content(idx)) {
    case CR | LF => true
  }

  /** True if the index is end of an EOL sequence. */
  def isAtEndOfLine(idx: Int): Boolean = charAtIsEOL(idx) {
    case CR | LF => true
    case _       => false
  }

  private lazy val lineIndices: Array[Int] = {
    val buf = new ArrayBuffer[Int]
    buf += 0
    val reader = new BufferedReader(new StringReader(code))
    try {
      var l = reader.readLine()
      var i = 0
      while (l != null) {
        i += l.length + 1
        buf += i
        l = reader.readLine()
      }
      buf += content.length // sentinel, so that findLine below works smoother
    } finally {
      reader.close()
    }
    buf.toArray
  }

  def lineToOffset(index: Int): Int = lineIndices(index)

  private var lastLine = 0

  /** Convert offset to line in this source file.
    *  Lines are numbered from 0.
    */
  def offsetToLine(offset: Int): Int = {
    val lines = lineIndices
    def findLine(lo: Int, hi: Int, mid: Int): Int =
      if (mid < lo || hi < mid) mid // minimal sanity check - as written this easily went into infinite loopyland
      else if (offset < lines(mid)) findLine(lo, mid - 1, (lo + mid - 1) / 2)
      else if (offset >= lines(mid + 1)) findLine(mid + 1, hi, (mid + 1 + hi) / 2)
      else mid
    lastLine = findLine(0, lines.length, lastLine)
    lastLine
  }

  override def equals(that: Any): Boolean = that match {
    case that: JawaSourceFile => file.path == that.file.path && start == that.start
    case _ => false
  }
  override def hashCode: Int = file.path.## + start.##
}

class JawaSourceFile(file: AbstractFile) extends DefaultSourceFile(file) {
  def getClassCodes: ISet[String] = {
    val c = code
    c.replaceAll("(record `)", "DELIMITER_JAWA_HAHAHA$1").split("DELIMITER_JAWA_HAHAHA").tail.toSet
  }
  def parse(reporter: Reporter): IMap[JawaType, MyClass] = JawaFileParser.parse(this, reporter)
}

class JavaSourceFile(global: Global, file: AbstractFile) extends DefaultSourceFile(file) {
  private var javacu: Option[JavaCompilationUnit] = None
  def getJavaCU: JavaCompilationUnit = {
    javacu match {
      case Some(cu) => cu
      case None =>
        val cu = JavaParser.parse(file.input)
        javacu = Some(cu)
        cu
    }
  }
  private def visitTypes(typ: JawaType, members: NodeList[BodyDeclaration[_ <: BodyDeclaration[_]]]): ISet[JawaType] = {
    val types: MSet[JawaType] = msetEmpty
    members.forEach {
      case td: TypeDeclaration[_] =>
        val innerTyp = new JawaType(s"${typ.jawaName}$$${td.getNameAsString}")
        types += innerTyp
        types ++= visitTypes(innerTyp, td.getMembers)
      case _ =>
    }
    types.toSet
  }
  def getTypes: ISet[JawaType] = {
    val types: MSet[JawaType] = msetEmpty
    val cu = getJavaCU
    var packageName = ""
    if(cu.getPackageDeclaration.isPresent) {
      packageName = cu.getPackageDeclaration.get().getName.asString() + "."
    }
    cu.getTypes.forEach{ typ =>
      val classType = new JawaType(s"$packageName${typ.getNameAsString}")
      types += classType
      types ++= visitTypes(classType, typ.getMembers)
    }
    types.toSet
  }
  def parse(reporter: Reporter): IMap[JawaType, MyClass] = JavaFileParser.parse(global, this, reporter)
}