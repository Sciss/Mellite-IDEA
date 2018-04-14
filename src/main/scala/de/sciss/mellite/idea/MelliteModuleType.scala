/*
 *  MelliteModuleType.scala
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

import com.intellij.icons.AllIcons
import com.intellij.openapi.module.ModuleType
import javax.swing.Icon

class MelliteModuleType extends ModuleType[MelliteModuleBuilder]("MELLITE_MODULE") {
  def createModuleBuilder() = new MelliteModuleBuilder

  def getName = "Mellite workspace"

  def getDescription =
    "Mellite workspace contain the entire collection of objects within a Mellite project."

  def getNodeIcon(isOpened: Boolean): Icon = AllIcons.Nodes.Module
}

object MelliteModuleType {
  // XXX TODO --- why use reflection here?
  val instance: MelliteModuleType =
    Class.forName("de.sciss.mellite.idea.MelliteModuleType").newInstance.asInstanceOf[MelliteModuleType]
}