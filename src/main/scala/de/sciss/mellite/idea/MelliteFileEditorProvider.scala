package de.sciss.mellite.idea

import com.intellij.openapi.fileEditor.{FileEditor, FileEditorPolicy, FileEditorProvider}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class MelliteFileEditorProvider extends FileEditorProvider {
  def accept(project: Project, file: VirtualFile): Boolean = {
//    val tpe = file.getFileType
//    val res = tpe.isInstanceOf[MelliteFileType]
//    res
    file.getName == "open" && Option(file.getParent).map(_.getExtension).contains("mllt")
  }

  def createEditor(project: Project, file: VirtualFile): FileEditor =
    new MelliteFileEditor(project, file)

  def getEditorTypeId: String = "mellite.file.editor"

  def getPolicy: FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
