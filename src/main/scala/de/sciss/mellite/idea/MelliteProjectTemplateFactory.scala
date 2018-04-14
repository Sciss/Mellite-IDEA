package de.sciss.mellite.idea

import com.intellij.ide.util.projectWizard.WizardContext
import com.intellij.platform.{ProjectTemplate, ProjectTemplatesFactory}
import javax.swing.Icon
import org.jetbrains.plugins.scala.icons.Icons
import org.jetbrains.plugins.scala.project.template.{ScalaProjectTemplatesFactory, WizardEntity}

class MelliteProjectTemplateFactory extends ProjectTemplatesFactory {
  def getGroups = Array("Mellite")

  override def getGroupIcon(group: String): Icon = Icons.SCALA_SMALL_LOGO

  def createTemplates(group: String, context: WizardContext): Array[ProjectTemplate] = {
    if (context.isCreatingNewProject)
      Array(new MelliteProjectTemplate(WizardEntity.Project))
    else Array.empty
  }
}