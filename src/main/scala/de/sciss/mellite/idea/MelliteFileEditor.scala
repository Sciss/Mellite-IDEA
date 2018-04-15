package de.sciss.mellite.idea

import java.beans.PropertyChangeListener

import com.intellij.codeHighlighting.BackgroundEditorHighlighter
import com.intellij.openapi.fileEditor.{FileEditor, FileEditorLocation, FileEditorState}
import com.intellij.openapi.util.UserDataHolderBase
import com.intellij.util.EventDispatcher
import de.sciss.desktop.UndoManager
import de.sciss.lucre.stm
import de.sciss.lucre.synth.Sys
import de.sciss.mellite.gui.FolderEditorView
import de.sciss.synth.proc.Workspace
import javax.swing.JComponent

class MelliteFileEditor[S <: Sys[S]]()(implicit ws: Workspace[S])
  extends UserDataHolderBase with FileEditor {

  private[this] val dispatcher = EventDispatcher.create(classOf[PropertyChangeListener])

  private[this] implicit val cursor: stm.Cursor[S] = ws.cursor

  private[this] val folderView = cursor.step { implicit tx =>
    implicit val undo: UndoManager = UndoManager()
    FolderEditorView[S](ws.root)
  }

  def getComponent: JComponent = folderView.component.peer

  def getPreferredFocusedComponent: JComponent = getComponent

  def getName: String = "Mellite Editor"

  def setState(state: FileEditorState): Unit = ()

  def isModified: Boolean = false
  def isValid   : Boolean = true

  def selectNotify()  : Unit = ()
  def deselectNotify(): Unit = ()

  def addPropertyChangeListener(listener: PropertyChangeListener): Unit =
    dispatcher.addListener(listener)

  def removePropertyChangeListener(listener: PropertyChangeListener): Unit =
    dispatcher.removeListener(listener)

  def getBackgroundHighlighter: BackgroundEditorHighlighter = null

  def getCurrentLocation: FileEditorLocation = null

  def dispose(): Unit = {
    cursor.step { implicit tx =>
      folderView.dispose()
    }
    ws.close()
  }
}
