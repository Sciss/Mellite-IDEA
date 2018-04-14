package de.sciss.mellite.idea

import com.intellij.openapi.fileEditor.{FileEditor, FileEditorPolicy, FileEditorProvider}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import de.sciss.file._
import de.sciss.lucre.stm.store.BerkeleyDB
import de.sciss.synth.proc.Workspace

class MelliteFileEditorProvider extends FileEditorProvider {
  def accept(project: Project, vf: VirtualFile): Boolean = {
//    val tpe = file.getFileType
//    val res = tpe.isInstanceOf[MelliteFileType]
//    res
    vf.getName == "open" && Option(vf.getParent).map(_.getExtension).contains("mllt")
  }

  def createEditor(project: Project, vf: VirtualFile): FileEditor = {
//    LocalFileSystem.getInstance()
    val bdbConfig = BerkeleyDB.Config()
    bdbConfig.allowCreate = false
    val dir = file(vf.getCanonicalPath).parent
    val bdb = BerkeleyDB.factory(dir, bdbConfig)
    val ws  = Workspace.Durable.read(dir, bdb)
    new MelliteFileEditor()(ws)
  }

  def getEditorTypeId: String = "mellite.file.editor"

  def getPolicy: FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
