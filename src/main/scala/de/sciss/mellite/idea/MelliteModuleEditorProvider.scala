/*
 *  MelliteModuleEditorProvider.scala
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

import com.intellij.openapi.module.ModuleConfigurationEditor
import com.intellij.openapi.roots.ui.configuration.{ModuleConfigurationEditorProvider, ModuleConfigurationState}

class MelliteModuleEditorProvider extends ModuleConfigurationEditorProvider {
  def createEditors(state: ModuleConfigurationState): Array[ModuleConfigurationEditor] =
    ModuleConfigurationEditor.EMPTY
}
