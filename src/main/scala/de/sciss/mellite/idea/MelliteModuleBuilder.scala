/*
 *  MelliteModuleBuilder.scala
 *  (Mellite-IDEA)
 *
 *  Copyright (c) 2018 Hanns Holger Rutz. All rights reserved.
 *
 *  This software is published under the GNU General Public License v3+
 *
 *
 *  For further information, please contact Hanns Holger Rutz at
 *  contact@sciss.de
 */

package de.sciss.mellite.idea

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel

class MelliteModuleBuilder extends ModuleBuilder {
  override def isOpenProjectSettingsAfter = true

  // override def canCreateModule = false

  def setupRootModel(modifiableRootModel: ModifiableRootModel): Unit = {
    println("TODO")
  }

  override def getModuleType: ModuleType[_ <: ModuleBuilder] = MelliteModuleType.instance

  override def getPresentableName = "Mellite Project"
  override def getGroupName       = "Mellite"
  override def isTemplateBased    = true
  override def getDescription     = "Project with a Mellite workspace."
}
