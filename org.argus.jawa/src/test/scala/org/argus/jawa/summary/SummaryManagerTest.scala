/*
 * Copyright (c) 2017. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.jawa.summary

import org.argus.jawa.alir.Context
import org.argus.jawa.alir.pta._
import org.argus.jawa.alir.pta.rfa.{RFAFact, SimHeap}
import org.argus.jawa.core.util._
import org.argus.jawa.core.{DefaultReporter, Global, JawaType, Signature}
import org.argus.jawa.summary.susaf.HeapSummaryProcessor
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by fgwei on 6/8/17.
  */
class SummaryManagerTest extends FlatSpec with Matchers {
  val reporter = new DefaultReporter
  val global = new Global("Test", reporter)
  "SummaryManager" should "handle += correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:()Ljava/lang/String;`:
        |  ret += java.lang.String@L1
        |  ret += "String"@L1
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val calleeSig = new Signature("Lmy/Class;.foo:()Ljava/lang/String;")
    val retName = "temp"
    val context = new Context("Test")
    val expectedContext = context.copy.setContext(calleeSig, "L1")
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(retName), PTAPointStringInstance(expectedContext)),
        new RFAFact(VarSlot(retName), PTAConcreteStringInstance("String", expectedContext))
      )

    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, Some(retName), None, ilistEmpty, isetEmpty[RFAFact], context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }

  "SummaryManager" should "handle = correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:()V`:
        |  this.f1 = my.Class2@L1
        |  this.f1.f2 = my.Class3@L2
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val callerSig = new Signature("Lmy/Class;.main:()V")
    val calleeSig = new Signature("Lmy/Class;.foo:()V")
    val recvName = "v1"
    val context = new Context("Test")
    val recvContext = context.copy.setContext(callerSig, "L0")
    val expectedContext1 = context.copy.setContext(calleeSig, "L1")
    val expectedContext2 = context.copy.setContext(calleeSig, "L2")
    val recvIns = PTAInstance(new JawaType("my.Class"), recvContext)
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext1)),
        new RFAFact(FieldSlot(PTAInstance(new JawaType("my.Class2"), expectedContext1), "f2"), PTAInstance(new JawaType("my.Class3"), expectedContext2))
      )

    val initialFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), context.copy.setContext(callerSig, "L100"))),
        new RFAFact(FieldSlot(PTAInstance(new JawaType("my.Class2"), context.copy.setContext(callerSig, "L100")), "f2"), PTAInstance(new JawaType("my.Class3"), context.copy.setContext(callerSig, "L101")))
      )
    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, None, Some(recvName), ilistEmpty, initialFacts, context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }

  "SummaryManager" should "handle -= correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:()V`:
        |  this.f1 -= this.f2
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val callerSig = new Signature("Lmy/Class;.main:()V")
    val calleeSig = new Signature("Lmy/Class;.foo:()V")
    val recvName = "v1"
    val context = new Context("Test")
    val recvContext = context.copy.setContext(callerSig, "L0")
    val expectedContext1 = context.copy.setContext(calleeSig, "L1")
    val expectedContext2 = context.copy.setContext(calleeSig, "L2")
    val recvIns = PTAInstance(new JawaType("my.Class"), recvContext)
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext1)),
        new RFAFact(FieldSlot(recvIns, "f2"), PTAInstance(new JawaType("my.Class2"), expectedContext2))
      )

    val initialFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext1)),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext2)),
        new RFAFact(FieldSlot(recvIns, "f2"), PTAInstance(new JawaType("my.Class2"), expectedContext2))
      )
    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, None, Some(recvName), ilistEmpty, initialFacts, context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }

  "SummaryManager" should "handle clear rule correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:()V`:
        |  this.f1 -= this.f2
        |  ~this.f2
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val callerSig = new Signature("Lmy/Class;.main:()V")
    val calleeSig = new Signature("Lmy/Class;.foo:()V")
    val recvName = "v1"
    val context = new Context("Test")
    val recvContext = context.copy.setContext(callerSig, "L0")
    val expectedContext1 = context.copy.setContext(calleeSig, "L1")
    val expectedContext2 = context.copy.setContext(calleeSig, "L2")
    val recvIns = PTAInstance(new JawaType("my.Class"), recvContext)
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext1)),
        new RFAFact(FieldSlot(recvIns, "f2"), PTAInstance(new JawaType("my.Class2"), expectedContext2))
      )

    val initialFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext1)),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class2"), expectedContext2)),
        new RFAFact(FieldSlot(recvIns, "f2"), PTAInstance(new JawaType("my.Class2"), expectedContext2)),
        new RFAFact(FieldSlot(PTAInstance(new JawaType("my.Class2"), expectedContext2), "ff1"), PTAInstance(new JawaType("my.Class3"), context.copy.setContext(callerSig, "L100")))
      )
    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, None, Some(recvName), ilistEmpty, initialFacts, context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }

  "SummaryManager" should "handle args correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:(Lmy/Class2;Ljava/lang/String;)V`:
        |  this.f1 += arg:0.f1
        |  arg:0.f2 = "String"@L10
        |  this.f2 = arg:1
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val callerSig = new Signature("Lmy/Class;.main:()V")
    val calleeSig = new Signature("Lmy/Class;.foo:(Lmy/Class2;Ljava/lang/String;)V")
    val recvName = "v1"
    val argNames = List("v2", "v3")
    val context = new Context("Test")
    val recvContext = context.copy.setContext(callerSig, "L0")
    val expectedContext1 = context.copy.setContext(calleeSig, "L1")
    val expectedContext2 = context.copy.setContext(calleeSig, "L2")
    val expectedContext3 = context.copy.setContext(calleeSig, "L3")
    val expectedContext10 = context.copy.setContext(calleeSig, "L10")
    val recvIns = PTAInstance(new JawaType("my.Class"), recvContext)
    val arg0Ins = PTAInstance(new JawaType("my.Class2"), expectedContext1)
    val arg1Ins = PTAConcreteStringInstance("taint", expectedContext3)
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(FieldSlot(recvIns, "f1"), PTAInstance(new JawaType("my.Class3"), expectedContext2)),
        new RFAFact(FieldSlot(recvIns, "f2"), arg1Ins),
        new RFAFact(VarSlot(argNames.head), arg0Ins),
        new RFAFact(FieldSlot(arg0Ins, "f1"), PTAInstance(new JawaType("my.Class3"), expectedContext2)),
        new RFAFact(FieldSlot(arg0Ins, "f2"), PTAConcreteStringInstance("String", expectedContext10)),
        new RFAFact(VarSlot(argNames(1)), arg1Ins)
      )

    val initialFacts: ISet[RFAFact] =
      Set(
        new RFAFact(VarSlot(recvName), recvIns),
        new RFAFact(VarSlot(argNames.head), arg0Ins),
        new RFAFact(FieldSlot(arg0Ins, "f1"), PTAInstance(new JawaType("my.Class3"), expectedContext2)),
        new RFAFact(VarSlot(argNames(1)), arg1Ins)
      )
    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, None, Some(recvName), argNames, initialFacts, context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }

  "SummaryManager" should "handle Global Variable correctly" in {
    val code =
      """
        |`Lmy/Class;.foo:()V`:
        |  `my.Class.Glo` += java.lang.String@L1
        |  `my.Class.Glo` += "String"@L1
        |;
      """.stripMargin
    implicit val factory: SimHeap = new SimHeap
    val sm = new SummaryManager(global)
    sm.register("test", code, fileAndSubsigMatch = false)

    val calleeSig = new Signature("Lmy/Class;.foo:()V")
    val globalFQN = "my.Class.Glo"
    val context = new Context("Test")
    val expectedContext = context.copy.setContext(calleeSig, "L1")
    val expectedFacts: ISet[RFAFact] =
      Set(
        new RFAFact(StaticFieldSlot(globalFQN), PTAPointStringInstance(expectedContext)),
        new RFAFact(StaticFieldSlot(globalFQN), PTAConcreteStringInstance("String", expectedContext))
      )

    val currentFacts: ISet[RFAFact] = HeapSummaryProcessor.process(global, sm, calleeSig, None, None, ilistEmpty, isetEmpty[RFAFact], context)
    assert(currentFacts.size == expectedFacts.size && currentFacts.diff(expectedFacts).isEmpty)
  }
}
