/*
 *  MelliteProjectTemplate.scala
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
import com.intellij.ide.util.projectWizard.AbstractModuleBuilder
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.platform.ProjectTemplate
import javax.swing.Icon
import org.jetbrains.plugins.scala.project.template.WizardEntity

class MelliteProjectTemplate(entity: WizardEntity) extends ProjectTemplate {
  def getName: String = "Mellite Project"

  def getDescription: String = entity match {
    case WizardEntity.Project  => "Project with a Mellite workspace."
    case WizardEntity.Module   => "Mellite Workspace"
  }

  def getIcon: Icon = entity match {
    case WizardEntity.Project  => AllIcons.Nodes.IdeaProject
    case WizardEntity.Module   => AllIcons.Nodes.Module
  }

  def createModuleBuilder(): AbstractModuleBuilder = new MelliteModuleBuilder

  /** Returns `null` if validation succeeds. */
  def validateSettings(): ValidationInfo = null
}
