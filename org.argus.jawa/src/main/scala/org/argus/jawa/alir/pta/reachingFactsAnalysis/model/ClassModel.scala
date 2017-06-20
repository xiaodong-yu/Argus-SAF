/*
 * Copyright (c) 2017. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jawa.alir.pta.reachingFactsAnalysis.model

import org.argus.jawa.alir.Context
import org.argus.jawa.alir.pta._
import org.argus.jawa.alir.pta.reachingFactsAnalysis.{RFAFact, SimHeap}
import org.argus.jawa.core.{JavaKnowledge, JawaMethod}
import org.argus.jawa.core.util._

/**
 * @author <a href="mailto:fgwei521@gmail.com">Fengguo Wei</a>
 */ 
class ClassModel extends ModelCall {
  val TITLE = "ClassModel"
	def isModelCall(p: JawaMethod): Boolean = p.getDeclaringClass.getName.equals("java.lang.Class")
	  
	def doModelCall(s: PTAResult, p: JawaMethod, args: List[String], retVar: String, currentContext: Context)(implicit factory: SimHeap): (ISet[RFAFact], ISet[RFAFact], Boolean) = {
	  var newFacts = isetEmpty[RFAFact]
	  var delFacts = isetEmpty[RFAFact]
	  var byPassFlag = true
	  p.getSignature.signature match{
	    case "Ljava/lang/Class;.<init>:()V" =>  //private constructor
		  case "Ljava/lang/Class;.arraycopy:([Ljava/lang/Object;[Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;" =>  //private static
		  case "Ljava/lang/Class;.asSubclass:(Ljava/lang/Class;)Ljava/lang/Class;" =>  //public
		    classAsSubClass(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.cast:(Ljava/lang/Object;)Ljava/lang/Object;" =>  //public
		    classAsSubClass(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.classForName:(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;" =>  //private static native
		    classForName(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.desiredAssertionStatus:()Z" =>  //public native
		  case "Ljava/lang/Class;.forName:(Ljava/lang/String;)Ljava/lang/Class;" =>  //public static
		    classForName(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.forName:(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;" =>  //public static
		    classForName(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;" =>  //public
		  case "Ljava/lang/Class;.getAnnotations:()[Ljava/lang/annotation/Annotation;" =>  //public
		  case "Ljava/lang/Class;.getCanonicalName:()Ljava/lang/String;" =>  //public
		  case "Ljava/lang/Class;.getClassLoader:()Ljava/lang/ClassLoader;" =>  //public
		  case "Ljava/lang/Class;.getClassLoader:(Ljava/lang/Class;)Ljava/lang/ClassLoader;" =>  //private static native
		  case "Ljava/lang/Class;.getClassLoaderImpl:()Ljava/lang/ClassLoader;" =>  //
		  case "Ljava/lang/Class;.getClasses:()[Ljava/lang/Class;" =>  //public
		  case "Ljava/lang/Class;.getComponentType:()Ljava/lang/Class;" =>  //public native
		    
		  case "Ljava/lang/Class;.getConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;" =>  //public
		  case "Ljava/lang/Class;.getConstructorOrMethod:(Ljava/lang/String;ZZ[Ljava/lang/Class;)Ljava/lang/reflect/Member;" =>  //private
		  case "Ljava/lang/Class;.getConstructors:()[Ljava/lang/reflect/Constructor;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;" =>  //private native
		  case "Ljava/lang/Class;.getDeclaredAnnotations:()[Ljava/lang/annotation/Annotation;" =>  //public native
		  case "Ljava/lang/Class;.getDeclaredClasses:()[Ljava/lang/Class;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredClasses:(Ljava/lang/Class;Z)[Ljava/lang/Class;" =>  //private static native
		  case "Ljava/lang/Class;.getDeclaredConstructor:([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredConstructorOrMethod:(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Member;" =>  //static native
		  case "Ljava/lang/Class;.getDeclaredConstructors:()[Ljava/lang/reflect/Constructor;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredConstructors:(Ljava/lang/Class;Z)[Ljava/lang/reflect/Constructor;" =>  //private static native
		  case "Ljava/lang/Class;.getDeclaredField:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;" =>  //static native
		  case "Ljava/lang/Class;.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredFields:()[Ljava/lang/reflect/Field;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredFields:(Ljava/lang/Class;Z)[Ljava/lang/reflect/Field;" =>  //static native
		  case "Ljava/lang/Class;.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredMethods:()[Ljava/lang/reflect/Method;" =>  //public
		  case "Ljava/lang/Class;.getDeclaredMethods:(Ljava/lang/Class;Z)[Ljava/lang/reflect/Method;" =>  //static native
		  case "Ljava/lang/Class;.getDeclaringClass:()Ljava/lang/Class;" =>  //public native
		  case "Ljava/lang/Class;.getEnclosingClass:()Ljava/lang/Class;" =>  //public native
		  case "Ljava/lang/Class;.getEnclosingConstructor:()Ljava/lang/reflect/Constructor;" =>  //public native
		  case "Ljava/lang/Class;.getEnclosingMethod:()Ljava/lang/reflect/Method;" =>  //public native
		  case "Ljava/lang/Class;.getEnumConstants:()[Ljava/lang/Object;" =>  //public
		  case "Ljava/lang/Class;.getField:(Ljava/lang/String;)Ljava/lang/reflect/Field;" =>  //public
		  case "Ljava/lang/Class;.getFields:()[Ljava/lang/reflect/Field;" =>  //public
		  case "Ljava/lang/Class;.getGenericInterfaces:()[Ljava/lang/reflect/Type;" =>  //public
		  case "Ljava/lang/Class;.getGenericSuperclass:()Ljava/lang/reflect/Type;" =>  //public
		  case "Ljava/lang/Class;.getInnerClassName:()Ljava/lang/String;" =>  //private native
		  case "Ljava/lang/Class;.getInterfaces:()[Ljava/lang/Class;" =>  //public native
		  case "Ljava/lang/Class;.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;" =>  //public
		  case "Ljava/lang/Class;.getMethods:()[Ljava/lang/reflect/Method;" =>  //public
		  case "Ljava/lang/Class;.getModifiers:()I" =>  //public
		  case "Ljava/lang/Class;.getModifiers:(Ljava/lang/Class;Z)I" =>  //private static native
		  case "Ljava/lang/Class;.getName:()Ljava/lang/String;" =>  //public
		    classGetName(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.getNameNative:()Ljava/lang/String;" =>  //private native
		    classGetName(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
		    byPassFlag = false
		  case "Ljava/lang/Class;.getPackage:()Ljava/lang/Package;" =>  //public
		  case "Ljava/lang/Class;.getProtectionDomain:()Ljava/security/ProtectionDomain;" =>  //public
		  case "Ljava/lang/Class;.getPublicConstructorOrMethodRecursive:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Member;" =>  //private
		  case "Ljava/lang/Class;.getPublicFieldRecursive:(Ljava/lang/String;)Ljava/lang/reflect/Field;" =>  //private
		  case "Ljava/lang/Class;.getPublicFieldsRecursive:(Ljava/util/List;)V" =>  //private
		  case "Ljava/lang/Class;.getPublicMethodsRecursive:(Ljava/util/List;)V" =>  //private
		  case "Ljava/lang/Class;.getResource:(Ljava/lang/String;)Ljava/net/URL;" =>  //public
		  case "Ljava/lang/Class;.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;" =>  //public
		  case "Ljava/lang/Class;.getSignatureAnnotation:()[Ljava/lang/Object;" =>  //private native
		  case "Ljava/lang/Class;.getSignatureAttribute:()Ljava/lang/String;" =>  //private
		  case "Ljava/lang/Class;.getSigners:()[Ljava/lang/Object;" =>  //public
		  case "Ljava/lang/Class;.getSimpleName:()Ljava/lang/String;" =>  //public
		  case "Ljava/lang/Class;.getSuperclass:()Ljava/lang/Class;" =>  //public native
		  case "Ljava/lang/Class;.getTypeParameters:()[Ljava/lang/reflect/TypeVariable;" =>  //public declared_synchronized
		  case "Ljava/lang/Class;.isAnnotation:()Z" =>  //public
		  case "Ljava/lang/Class;.isAnnotationPresent:(Ljava/lang/Class;)Z" =>  //public
		  case "Ljava/lang/Class;.isAnonymousClass:()Z" =>  //public native
		  case "Ljava/lang/Class;.isArray:()Z" =>  //public
		  case "Ljava/lang/Class;.isAssignableFrom:(Ljava/lang/Class;)Z" =>  //public native
		  case "Ljava/lang/Class;.isDeclaredAnnotationPresent:(Ljava/lang/Class;)Z" =>  //private native
		  case "Ljava/lang/Class;.isEnum:()Z" =>  //public
		  case "Ljava/lang/Class;.isInstance:(Ljava/lang/Object;)Z" =>  //public native
		  case "Ljava/lang/Class;.isInterface:()Z" =>  //public native
		  case "Ljava/lang/Class;.isLocalClass:()Z" =>  //public
		  case "Ljava/lang/Class;.isMemberClass:()Z" =>  //public
		  case "Ljava/lang/Class;.isPrimitive:()Z" =>  //public native
		  case "Ljava/lang/Class;.isSynthetic:()Z" =>  //public
		  case "Ljava/lang/Class;.newInstance:()Ljava/lang/Object;" =>  //public
        classNewInstance(s, args, retVar, currentContext) match{case (n, d) => newFacts ++= n; delFacts ++= d}
        byPassFlag = false
		  case "Ljava/lang/Class;.newInstanceImpl:()Ljava/lang/Object;" =>  //private native
		  case "Ljava/lang/Class;.toString:()Ljava/lang/String;" =>  //public
	  }
	  (newFacts, delFacts, byPassFlag)
	}
	
	
	/**
   * Ljava/lang/Class;.asSubclass:(Ljava/lang/Class;)Ljava/lang/Class;
   */
  private def classAsSubClass(s: PTAResult, args: List[String], retVar: String, currentContext: Context)(implicit factory: SimHeap): (ISet[RFAFact], ISet[RFAFact]) = {
    require(args.size >1)
    val thisSlot = VarSlot(args.head)
	  val thisValue = s.pointsToSet(after = false, currentContext, thisSlot)
	  var newfacts = isetEmpty[RFAFact]
    val delfacts = isetEmpty[RFAFact]
	  thisValue.foreach{ tv =>
			newfacts += new RFAFact(VarSlot(retVar), tv)
	  }
    (newfacts, delfacts)
  }
  
  /**
   * Ljava/lang/Class;.asSubclass:(Ljava/lang/Class;)Ljava/lang/Class;
   */
//  private def classCast(s: PTAResult, args: List[String], retVar: String, currentContext: Context)(implicit factory: RFAFactFactory): (ISet[RFAFact], ISet[RFAFact]) = {
//    require(args.size >1)
//    val paramSlot = VarSlot(args(1))
//	  val paramValue = s.pointsToSet(paramSlot, currentContext)
//	  var newfacts = isetEmpty[RFAFact]
//    val delfacts = isetEmpty[RFAFact]
//	  paramValue.foreach{
//	    pv =>
//	      newfacts += new RFAFact(VarSlot(retVar), pv)
//	  }
//    (newfacts, delfacts)
//  }
  
  /**
	 * Ljava/lang/Class;.forName:(Ljava/lang/String;)Ljava/lang/Class;   static
	 */
	private def classForName(s: PTAResult, args: List[String], retVar: String, currentContext: Context)(implicit factory: SimHeap): (ISet[RFAFact], ISet[RFAFact]) = {
	  // algo:thisValue.foreach.{ cIns => get value of (cIns.name") and create fact (retVar, value)}
    require(args.nonEmpty)
    val clazzNameSlot = VarSlot(args.head)
    val clazzNameValue = s.pointsToSet(after = false, currentContext, clazzNameSlot)
    var newfacts = isetEmpty[RFAFact]
    val delfacts = isetEmpty[RFAFact]
    clazzNameValue.foreach {
			case _@PTAConcreteStringInstance(text, _) =>
				val classType = JavaKnowledge.getTypeFromJawaName(text)
				newfacts += new RFAFact(VarSlot(retVar), ClassInstance(classType, currentContext))
			case _@PTAPointStringInstance(_) =>
			//            System.err.println(TITLE, "Get class use point string: " + pstr)
			case _ =>
			//            System.err.println(TITLE, "Get class use unknown instance: " + cIns)
		}
    (newfacts, delfacts)
	}
  
  private def classNewInstance(s: PTAResult, args: List[String], retVar: String, currentContext: Context)(implicit factory: SimHeap): (ISet[RFAFact], ISet[RFAFact]) = {
    require(args.nonEmpty)
    val classSlot = VarSlot(args.head)
    val classValue = s.pointsToSet(after = false, currentContext, classSlot)
    var newfacts = isetEmpty[RFAFact]
    val delfacts = isetEmpty[RFAFact]
    classValue.foreach {
			case _@ClassInstance(typ, _) =>
				newfacts += new RFAFact(VarSlot(retVar), PTAInstance(typ, currentContext))
			case _ =>
		}
    (newfacts, delfacts)
  }
	
	/**
	 * Ljava/lang/Class;.getName:()Ljava/lang/String;
	 */
	private def classGetName(s: PTAResult, args: List[String], retVar: String, currentContext: Context)(implicit factory: SimHeap): (ISet[RFAFact], ISet[RFAFact]) = {
	  // algo:thisValue.foreach.{ cIns => get value of (cIns.name") and create fact (retVar, value)}
    require(args.nonEmpty)
    val thisSlot = VarSlot(args.head)
    val thisValue = s.pointsToSet(after = false, currentContext, thisSlot)
    var newfacts = isetEmpty[RFAFact]
    val delfacts = isetEmpty[RFAFact]
    thisValue.foreach {
			case cIns: ClassInstance =>
				val name = cIns.getName
				val strIns = PTAConcreteStringInstance(name, cIns.defSite)
				newfacts += new RFAFact(VarSlot(retVar), strIns)
			case cIns =>
				val strIns = PTAPointStringInstance(cIns.defSite)
				newfacts += new RFAFact(VarSlot(retVar), strIns)
		}
    (newfacts, delfacts)
	}
	
}
