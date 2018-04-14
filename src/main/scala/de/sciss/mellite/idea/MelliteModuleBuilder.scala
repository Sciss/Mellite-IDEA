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

import java.io.File

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.openapi.vfs.{LocalFileSystem, VirtualFile}
import de.sciss.file._
import de.sciss.lucre.stm.store.BerkeleyDB
import de.sciss.synth.proc.Workspace

class MelliteModuleBuilder extends ModuleBuilder {
  override def isOpenProjectSettingsAfter = true

  // override def canCreateModule = false

  def setupRootModel(model: ModifiableRootModel): Unit =
    tryToSetupRootModel(model, getContentEntryPath)

  override def getModuleType: ModuleType[_ <: ModuleBuilder] = MelliteModuleType.instance

  override def getPresentableName = "Mellite Project"
  override def getGroupName       = "Mellite"
  override def isTemplateBased    = true
  override def getDescription     = "Project with a Mellite workspace."

  // --------

  private def tryToSetupRootModel(model: ModifiableRootModel, contentEntryPath: String): Boolean = {
    val attempt = for {
      contentPath <- Option(contentEntryPath)
      if contentPath.nonEmpty
      contentRootDir = new File(contentPath)
      if FileUtilRt.createDirectory(contentRootDir)
      vContentRootDir <- Option(LocalFileSystem.getInstance.refreshAndFindFileByIoFile(contentRootDir))
    } yield {
      doSetupRootModel(model, contentRootDir, vContentRootDir)
      ()
    }

    attempt.isDefined
  }

  private def doSetupRootModel(model: ModifiableRootModel, contentRootDir: File,
                               vContentRootDir: VirtualFile): Unit = {
//    val tpe = vContentRootDir.getFileType
//    model.addContentEntry(vContentRootDir)
    val workspaceF  = contentRootDir / s"workspace.${Workspace.ext}"
    val bdbConfig   = BerkeleyDB.Config()
    val bdb         = BerkeleyDB.factory(workspaceF, bdbConfig)
    val workspace   = Workspace.Durable.empty(workspaceF, bdb)
    workspace.close()

    val workspaceVF = LocalFileSystem.getInstance.refreshAndFindFileByIoFile(workspaceF / "open")
//    val tpe = workspaceVF.getFileType
    model.addContentEntry(workspaceVF)

    //    val module      = model.getModule
//    val rootPath    = contentRootDir.getCanonicalPath
  }
}
