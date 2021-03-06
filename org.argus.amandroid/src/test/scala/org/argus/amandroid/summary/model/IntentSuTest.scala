/*
 * Copyright (c) 2017. Fengguo Wei and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Detailed contributors are listed in the CONTRIBUTOR.md
 */

package org.argus.amandroid.summary.model

import org.argus.amandroid.core.AndroidConstants
import org.argus.jawa.alir.pta._
import org.argus.jawa.alir.pta.rfa.RFAFact
import org.argus.jawa.core.{JavaKnowledge, JawaType}

/**
  * Created by fgwei on 6/24/17.
  */
class IntentSuTest extends SuTestBase("Intent.safsu") {

  val thisInstance = PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)
  val thisFact = new RFAFact(VarSlot("v0"), thisInstance)
  val thisMComponentInstance = PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext)
  val thisMComponentFact = new RFAFact(FieldSlot(thisInstance, "mComponent"), thisMComponentInstance)
  val thisMActionInstance = PTAConcreteStringInstance("my.Action", defContext)
  val thisMActionFact = new RFAFact(FieldSlot(thisInstance, "mAction"), thisMActionInstance)
  val thisMCategoriesInstance = PTAConcreteStringInstance("my.Category", defContext)
  val thisMCategoriesFact = new RFAFact(FieldSlot(thisInstance, "mCategories"), thisMCategoriesInstance)
  val thisMTypeInstance = PTAConcreteStringInstance("my.Type", defContext)
  val thisMTypeFact = new RFAFact(FieldSlot(thisInstance, "mType"), thisMTypeInstance)
  val thisMDataInstance = PTAInstance(new JawaType(AndroidConstants.URI), defContext)
  val thisMDataFact = new RFAFact(FieldSlot(thisInstance, "mData"), thisMDataInstance)
  val thisMExtrasInstance = PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext)
  val thisMExtrasFact = new RFAFact(FieldSlot(thisInstance, "mExtras"), thisMExtrasInstance)
  val thisMExtrasEntriesInstance = PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext)
  val thisMExtrasEntriesFact = new RFAFact(FieldSlot(thisMExtrasInstance, "entries"), thisMExtrasEntriesInstance)

  "Landroid/content/Intent;.<clinit>:()V" with_input () produce ()

  "Landroid/content/Intent;.<init>:()V" with_input (
    thisFact,
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.<init>:(Landroid/content/Context;Ljava/lang/Class;)V" with_input (
    thisFact,
    new RFAFact(VarSlot("v2"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("com.Class", defContext2))
  ) produce (
    thisFact,
    new RFAFact(VarSlot("v2"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("com.Class", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext), "mClass"), PTAConcreteStringInstance("com.Class", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.<init>:(Landroid/content/Intent;)V" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2))
  ) produce (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2))
  )

  "Landroid/content/Intent;.<init>:(Ljava/lang/String;)V" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2))
  ) produce (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.<init>:(Ljava/lang/String;Landroid/net/Uri;)V" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2))
  ) produce (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.<init>:(Ljava/lang/String;Landroid/net/Uri;Landroid/content/Context;Ljava/lang/Class;)V" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v4"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("my.Class", defContext2))
  ) produce (
    thisFact,
    thisFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v4"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.addCategory:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Category2", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisInstance, "mCategories"), PTAConcreteStringInstance("my.Category2", defContext2)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Category2", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.addFlags:(I)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.clone:()Ljava/lang/Object;" with_input (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "mClass"), PTAConcreteStringInstance("my.Class", defContext)),
    thisMActionFact,
    thisMCategoriesFact,
    thisMTypeFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext))
  ) produce (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "mClass"), PTAConcreteStringInstance("my.Class", defContext)),
    thisMActionFact,
    thisMCategoriesFact,
    thisMTypeFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mComponent"), thisMComponentInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mAction"), thisMActionInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mType"), thisMTypeInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mData"), thisMDataInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mCategories"), thisMCategoriesInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mExtras"), thisMExtrasInstance)
  )

  "Landroid/content/Intent;.cloneFilter:()Landroid/content/Intent;" with_input (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "mClass"), PTAConcreteStringInstance("my.Class", defContext)),
    thisMActionFact,
    thisMCategoriesFact,
    thisMTypeFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext))
  ) produce (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "mClass"), PTAConcreteStringInstance("my.Class", defContext)),
    thisMActionFact,
    thisMCategoriesFact,
    thisMTypeFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mComponent"), thisMComponentInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mAction"), thisMActionInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mType"), thisMTypeInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mData"), thisMDataInstance),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mCategories"), thisMCategoriesInstance)
  )

  "Landroid/content/Intent;.createChooser:(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;" with_input (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2))
  ) produce (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2))
  )

  "Landroid/content/Intent;.describeContents:()I" with_input () produce ()

  "Landroid/content/Intent;.fillIn:(Landroid/content/Intent;I)I" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2))
  ) produce (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mCategories"), PTAConcreteStringInstance("my.Category", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext2))
  )

  "Landroid/content/Intent;.filterEquals:(Landroid/content/Intent;)Z" with_input () produce ()

  "Landroid/content/Intent;.filterHashCode:()I" with_input () produce ()

  "Landroid/content/Intent;.getAction:()Ljava/lang/String;" with_input (
    thisFact,
    thisMActionFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMActionFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisMActionInstance)
  )

  "Landroid/content/Intent;.getBooleanArrayExtra:(Ljava/lang/String;)[Z" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("boolean", 1), defContext))
  )

  "Landroid/content/Intent;.getBooleanExtra:(Ljava/lang/String;Z)Z" with_input () produce ()

  "Landroid/content/Intent;.getBundleExtra:(Ljava/lang/String;)Landroid/os/Bundle;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext))
  )

  "Landroid/content/Intent;.getByteArrayExtra:(Ljava/lang/String;)[B" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("byte", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("byte", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("byte", 1), defContext))
  )

  "Landroid/content/Intent;.getByteExtra:(Ljava/lang/String;B)B" with_input () produce ()

  "Landroid/content/Intent;.getCategories:()Ljava/util/Set;" with_input (
    thisFact,
    thisMCategoriesFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMCategoriesFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.util.HashSet"), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("java.util.HashSet"), currentContext), "items"), thisMCategoriesInstance)
  )

  "Landroid/content/Intent;.getCharArrayExtra:(Ljava/lang/String;)[C" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("char", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("char", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("char", 1), defContext))
  )

  "Landroid/content/Intent;.getCharExtra:(Ljava/lang/String;C)C" with_input () produce ()

  "Landroid/content/Intent;.getCharSequenceArrayExtra:(Ljava/lang/String;)[Ljava/lang/CharSequence;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext))
  )

  "Landroid/content/Intent;.getCharSequenceArrayListExtra:(Ljava/lang/String;)Ljava/util/ArrayList;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.util.ArrayList"), defContext))
  )

  "Landroid/content/Intent;.getCharSequenceExtra:(Ljava/lang/String;)Ljava/lang/CharSequence;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAPointStringInstance(defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAPointStringInstance(defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(defContext))
  )

  "Landroid/content/Intent;.getClipData:()Landroid/content/ClipData;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("android.content.ClipData"), currentContext))
  )

  "Landroid/content/Intent;.getComponent:()Landroid/content/ComponentName;" with_input (
    thisFact,
    thisMComponentFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMComponentFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisMComponentInstance)
  )

  "Landroid/content/Intent;.getData:()Landroid/net/Uri;" with_input (
    thisFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisMDataInstance)
  )

  "Landroid/content/Intent;.getDataString:()Ljava/lang/String;" with_input (
    thisFact,
    thisMDataFact,
    new RFAFact(FieldSlot(thisMDataInstance, "uri"), PTAConcreteStringInstance("content://my.uri", defContext)),
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMDataFact,
    new RFAFact(FieldSlot(thisMDataInstance, "uri"), PTAConcreteStringInstance("content://my.uri", defContext)),
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAConcreteStringInstance("content://my.uri", defContext))
  )

  "Landroid/content/Intent;.getDoubleArrayExtra:(Ljava/lang/String;)[D" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("double", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("double", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("double", 1), defContext))
  )

  "Landroid/content/Intent;.getDoubleExtra:(Ljava/lang/String;D)D" with_input () produce ()

  "Landroid/content/Intent;.getExtra:(Ljava/lang/String;)Ljava/lang/Object;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("boolean", 1), defContext))
  )

  "Landroid/content/Intent;.getExtra:(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("boolean", 1), defContext2))
  )

  "Landroid/content/Intent;.getExtras:()Landroid/os/Bundle;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("boolean", 1), defContext)),
    new RFAFact(VarSlot("temp"), thisMExtrasInstance)
  )

  "Landroid/content/Intent;.getFlags:()I" with_input () produce ()

  "Landroid/content/Intent;.getFloatArrayExtra:(Ljava/lang/String;)[F" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("float", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("float", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("float", 1), defContext))
  )

  "Landroid/content/Intent;.getFloatExtra:(Ljava/lang/String;F)F" with_input () produce ()

  "Landroid/content/Intent;.getIBinderExtra:(Ljava/lang/String;)Landroid/os/IBinder;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("android.os.Binder"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("android.os.Binder"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("android.os.Binder"), defContext))
  )

  "Landroid/content/Intent;.getIntArrayExtra:(Ljava/lang/String;)[I" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("int", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("int", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("int", 1), defContext))
  )

  "Landroid/content/Intent;.getIntExtra:(Ljava/lang/String;I)I" with_input () produce ()

  "Landroid/content/Intent;.getIntegerArrayListExtra:(Ljava/lang/String;)Ljava/util/ArrayList;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.util.ArrayList"), defContext))
  )

  "Landroid/content/Intent;.getIntent:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext))
  )

  "Landroid/content/Intent;.getIntentOld:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext))
  )

  "Landroid/content/Intent;.getLongArrayExtra:(Ljava/lang/String;)[J" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("long", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("long", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("long", 1), defContext))
  )

  "Landroid/content/Intent;.getLongExtra:(Ljava/lang/String;J)J" with_input () produce ()

  "Landroid/content/Intent;.getPackage:()Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.getParcelableArrayExtra:(Ljava/lang/String;)[Landroid/os/Parcelable;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext))
  )

  "Landroid/content/Intent;.getParcelableArrayListExtra:(Ljava/lang/String;)Ljava/util/ArrayList;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.util.ArrayList"), defContext))
  )

  "Landroid/content/Intent;.getParcelableExtra:(Ljava/lang/String;)Landroid/os/Parcelable;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext))
  )

  "Landroid/content/Intent;.getScheme:()Ljava/lang/String;" with_input (
    thisFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMDataInstance, "scheme"), PTAConcreteStringInstance("my.Scheme", defContext))
  ) with_input (
    thisFact,
    thisMDataFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMDataInstance, "scheme"), PTAConcreteStringInstance("my.Scheme", defContext)),
    new RFAFact(VarSlot("temp"), PTAConcreteStringInstance("my.Scheme", defContext))
  )

  "Landroid/content/Intent;.getSelector:()Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.getSerializableExtra:(Ljava/lang/String;)Ljava/io/Serializable;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext))
  )

  "Landroid/content/Intent;.getShortArrayExtra:(Ljava/lang/String;)[S" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("short", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("short", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("short", 1), defContext))
  )

  "Landroid/content/Intent;.getShortExtra:(Ljava/lang/String;S)S" with_input () produce ()

  "Landroid/content/Intent;.getSourceBounds:()Landroid/graphics/Rect;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("android.graphics.Rect"), currentContext))
  )

  "Landroid/content/Intent;.getStringArrayExtra:(Ljava/lang/String;)[Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.String", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.String", 1), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.lang.String", 1), defContext))
  )

  "Landroid/content/Intent;.getStringArrayListExtra:(Ljava/lang/String;)Ljava/util/ArrayList;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.util.ArrayList"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.util.ArrayList"), defContext))
  )

  "Landroid/content/Intent;.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.String"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAInstance(new JawaType("java.lang.String"), defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("java.lang.String"), defContext))
  )

  "Landroid/content/Intent;.getType:()Ljava/lang/String;" with_input (
    thisFact,
    thisMTypeFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMTypeFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisMTypeInstance)
  )

  "Landroid/content/Intent;.hasCategory:(Ljava/lang/String;)Z" with_input () produce ()

  "Landroid/content/Intent;.hasExtra:(Ljava/lang/String;)Z" with_input () produce ()

  "Landroid/content/Intent;.hasFileDescriptors:()Z" with_input () produce ()

  "Landroid/content/Intent;.isExcludingStopped:()Z" with_input () produce ()

  "Landroid/content/Intent;.makeMainActivity:(Landroid/content/ComponentName;)Landroid/content/Intent;" with_input (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext), "mClass"), PTAConcreteStringInstance("my.Activity", defContext))
  ) produce (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext), "mClass"), PTAConcreteStringInstance("my.Activity", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext))
  )

  "Landroid/content/Intent;.makeMainSelectorActivity:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Category", defContext))
  ) produce (
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Category", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mAction"), PTAConcreteStringInstance("my.Action", defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mCategories"), PTAConcreteStringInstance("my.Category", defContext))
  )

  "Landroid/content/Intent;.makeRestartActivityTask:(Landroid/content/ComponentName;)Landroid/content/Intent;" with_input (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext), "mClass"), PTAConcreteStringInstance("my.Activity", defContext))
  ) produce (
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext), "mClass"), PTAConcreteStringInstance("my.Activity", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext))
  )

  "Landroid/content/Intent;.migrateExtraStreamToClipData:()Z" with_input () produce ()

  "Landroid/content/Intent;.normalizeMimeType:(Ljava/lang/String;)Ljava/lang/String;" with_input (
    thisFact,
    thisMTypeFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMTypeFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisMTypeInstance)
  )

  "Landroid/content/Intent;.parseIntent:(Landroid/content/res/Resources;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;)Landroid/content/Intent;" with_input (
  ) produce (
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mAction"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mType"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), currentContext), "uri"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mCategories"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext)),
  )

  "Landroid/content/Intent;.parseUri:(Ljava/lang/String;I)Landroid/content/Intent;" with_input (
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("content://my.uri", defContext)),
  ) produce (
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("content://my.uri", defContext)),
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mData"), PTAInstance(new JawaType(AndroidConstants.URI), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), currentContext), "uri"), PTAConcreteStringInstance("content://my.uri", defContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), currentContext), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.putCharSequenceArrayListExtra:(Ljava/lang/String;Ljava/util/ArrayList;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;B)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;C)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;D)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;F)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;I)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;J)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Landroid/os/Bundle;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Landroid/os/IBinder;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("android.os.Binder"), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("android.os.Binder"), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("android.os.Binder"), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Ljava/lang/CharSequence;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;S)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;Z)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[B)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("boolean", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[C)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("char", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("char", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("char", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[D)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("double", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("double", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("double", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[F)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("float", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("float", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("float", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[I)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("int", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("int", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("int", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[J)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("long", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("long", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("long", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[Landroid/os/Parcelable;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("android.os.Parcelable", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("android.os.Parcelable", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("android.os.Parcelable", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[Ljava/lang/CharSequence;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.lang.CharSequence", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.lang.String", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.lang.String", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.lang.String", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[S)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("short", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("short", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("short", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtra:(Ljava/lang/String;[Z)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("boolean", 1), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("boolean", 1), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtras:(Landroid/content/Intent;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasInstance, "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putExtras:(Landroid/os/Bundle;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasInstance, "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putIntegerArrayListExtra:(Ljava/lang/String;Ljava/util/ArrayList;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putParcelableArrayListExtra:(Ljava/lang/String;Ljava/util/ArrayList;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.putStringArrayListExtra:(Ljava/lang/String;Ljava/util/ArrayList;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext2)), PTAInstance(new JawaType("java.util.ArrayList"), defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.readFromParcel:(Landroid/os/Parcel;)V" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), currentContext), "uri"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(thisInstance, "mCategories"), PTAPointStringInstance(currentContext)),
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), currentContext), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), currentContext))
  )

  "Landroid/content/Intent;.removeCategory:(Ljava/lang/String;)V" with_input (
    thisFact,
    thisMActionFact,
    thisMCategoriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Category", defContext))
  ) produce (
    thisFact,
    thisMActionFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Category", defContext))
  )

  "Landroid/content/Intent;.removeExtra:(Ljava/lang/String;)V" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("key", defContext))
  )

  "Landroid/content/Intent;.replaceExtras:(Landroid/content/Intent;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.INTENT), defContext2), "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.replaceExtras:(Landroid/os/Bundle;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,new RFAFact(FieldSlot(thisMExtrasEntriesInstance, "key"), PTAConcreteStringInstance("key", defContext)),
    new RFAFact(MapSlot(thisMExtrasEntriesInstance, PTAConcreteStringInstance("key", defContext)), PTAConcreteStringInstance("value", defContext)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mExtras"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.BUNDLE), defContext2), "entries"), PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), "key"), PTAConcreteStringInstance("key", defContext2)),
    new RFAFact(MapSlot(PTAInstance(new JawaType("android.os.Bundle$Entries"), defContext2), PTAConcreteStringInstance("key", defContext2)), PTAConcreteStringInstance("value", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.resolveActivity:(Landroid/content/pm/PackageManager;)Landroid/content/ComponentName;" with_input (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "uri"), PTAConcreteStringInstance("content://my.uri", defContext))
  ) produce (
    thisFact,
    thisMComponentFact,
    new RFAFact(FieldSlot(thisMComponentInstance, "uri"), PTAConcreteStringInstance("content://my.uri", defContext)),
    new RFAFact(VarSlot("temp"), thisMComponentInstance)
  )

  "Landroid/content/Intent;.resolveActivityInfo:(Landroid/content/pm/PackageManager;I)Landroid/content/pm/ActivityInfo;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAInstance(new JawaType("android.content.pm.ActivityInfo"), currentContext))
  )

  "Landroid/content/Intent;.resolveType:(Landroid/content/ContentResolver;)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.resolveType:(Landroid/content/Context;)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.resolveTypeIfNeeded:(Landroid/content/ContentResolver;)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.setAction:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMActionFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mAction"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Action", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setAllowFds:(Z)V" with_input () produce ()

  "Landroid/content/Intent;.setClass:(Landroid/content/Context;Ljava/lang/Class;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v2"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("my.Class", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("v2"), PTAInstance(JavaKnowledge.CLASS, defContext2)),
    new RFAFact(FieldSlot(PTAInstance(JavaKnowledge.CLASS, defContext2), "name"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setClassName:(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Class", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setClassName:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Class", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), currentContext), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setClipData:(Landroid/content/ClipData;)V" with_input () produce ()

  "Landroid/content/Intent;.setComponent:(Landroid/content/ComponentName;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mComponent"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.COMPONENT_NAME), defContext2), "mClass"), PTAConcreteStringInstance("my.Class", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setData:(Landroid/net/Uri;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMDataFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.Action", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.Action", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setDataAndNormalize:(Landroid/net/Uri;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMDataFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.Action", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.Action", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setDataAndType:(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Type", defContext3))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext3)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Type", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setDataAndTypeAndNormalize:(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Type", defContext3))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mData"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext3)),
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType(AndroidConstants.URI), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType(AndroidConstants.URI), defContext2), "uri"), PTAConcreteStringInstance("content://my.uri", defContext2)),
    new RFAFact(VarSlot("v2"), PTAConcreteStringInstance("my.Type", defContext3)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setExtrasClassLoader:(Ljava/lang/ClassLoader;)V" with_input () produce ()

  "Landroid/content/Intent;.setFlags:(I)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setPackage:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setSelector:(Landroid/content/Intent;)V" with_input () produce ()

  "Landroid/content/Intent;.setSourceBounds:(Landroid/graphics/Rect;)V" with_input () produce ()

  "Landroid/content/Intent;.setType:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Type", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.setTypeAndNormalize:(Ljava/lang/String;)Landroid/content/Intent;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Type", defContext2))
  ) produce (
    thisFact,
    new RFAFact(FieldSlot(thisInstance, "mType"), PTAConcreteStringInstance("my.Type", defContext2)),
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAConcreteStringInstance("my.Type", defContext2)),
    new RFAFact(VarSlot("temp"), thisInstance)
  )

  "Landroid/content/Intent;.toInsecureString:()Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toInsecureStringWithClip:()Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toShortString:(Ljava/lang/StringBuilder;ZZZZ)V" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType("java.lang.StringBuilder"), defContext2))
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("v1"), PTAInstance(new JawaType("java.lang.StringBuilder"), defContext2)),
    new RFAFact(FieldSlot(PTAInstance(new JawaType("java.lang.StringBuilder"), defContext2), "value"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toShortString:(ZZZZ)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toString:()Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toURI:()Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.toUri:(I)Ljava/lang/String;" with_input (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact
  ) produce (
    thisFact,
    thisMExtrasFact,
    thisMExtrasEntriesFact,
    new RFAFact(VarSlot("temp"), PTAPointStringInstance(currentContext))
  )

  "Landroid/content/Intent;.writeToParcel:(Landroid/os/Parcel;I)V" with_input () produce ()
}
