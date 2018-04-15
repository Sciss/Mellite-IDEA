package de.sciss.mellite.idea

import com.intellij.openapi.fileEditor.{FileEditor, FileEditorPolicy, FileEditorProvider}
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.WindowManager
import de.sciss.desktop.WindowHandler
import de.sciss.desktop.impl.{WindowImpl, WindowStub}
import de.sciss.file._
import de.sciss.lucre.stm.store.BerkeleyDB
import de.sciss.mellite.{Application, Mellite}
import de.sciss.synth.proc.Workspace
import javax.swing.UIManager

object MelliteFileEditorProvider {
  private lazy val _init: Unit = {
    val laf = UIManager.getLookAndFeel
    val lafId   = laf.getID
    if (lafId == "Darcula") UIManager.put("dark-skin", true)
    Mellite.initTypes()
    Application.init(Mellite)
  }
}
class MelliteFileEditorProvider extends FileEditorProvider {
  def accept(project: Project, vf: VirtualFile): Boolean = {
//    val tpe = file.getFileType
//    val res = tpe.isInstanceOf[MelliteFileType]
//    res
    vf.getName == "open" && Option(vf.getParent).map(_.getExtension).contains("mllt")
  }

  def createEditor(project: Project, vf: VirtualFile): FileEditor = {
//    LocalFileSystem.getInstance()
    MelliteFileEditorProvider._init
    val bdbConfig = BerkeleyDB.Config()
    bdbConfig.allowCreate = false
    val dir = file(vf.getCanonicalPath).parent
    val bdb = BerkeleyDB.factory(dir, bdbConfig)
    val ws  = Workspace.Durable.read(dir, bdb)
    val frame = WindowManager.getInstance().getFrame(project)
    val rp    = frame.getRootPane
    val key   = "de.sciss.desktop.Window" /* WindowImpl.Property */
    val desktopOk = rp.getClientProperty(key) != null
    if (!desktopOk) {
      val value = new WindowStub {
        protected val delegate: WindowImpl.Delegate =
          WindowImpl.Delegate.frame(this, frame, hasMenuBar = false, screen = false)

        def handler: WindowHandler = Mellite.windowHandler
      }
      rp.putClientProperty(key, value)
    }

    new MelliteFileEditor()(ws)
  }

  def getEditorTypeId: String = "mellite.file.editor"

  def getPolicy: FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}
