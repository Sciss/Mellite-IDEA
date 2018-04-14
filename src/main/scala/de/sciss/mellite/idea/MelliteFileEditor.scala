package de.sciss.mellite.idea

import java.beans.PropertyChangeListener

import com.intellij.codeHighlighting.BackgroundEditorHighlighter
import com.intellij.openapi.fileEditor.{FileEditor, FileEditorLocation, FileEditorState}
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.{Key, UserDataHolderBase}
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.EventDispatcher
import javax.swing.JComponent

import scala.swing.Label

class MelliteFileEditor(project: Project, file: VirtualFile)
  extends UserDataHolderBase with FileEditor {

  private[this] val disp = EventDispatcher.create(classOf[PropertyChangeListener])

  private[this] val c = new Label("FOO BAR")

  def getComponent: JComponent = c.peer

  def getPreferredFocusedComponent: JComponent = getComponent

  def getName: String = "Mellite Editor"

  def setState(state: FileEditorState): Unit = ()

  def isModified: Boolean = false
  def isValid   : Boolean = true

  def selectNotify()  : Unit = ()
  def deselectNotify(): Unit = ()

  def addPropertyChangeListener(listener: PropertyChangeListener): Unit =
    disp.addListener(listener)

  def removePropertyChangeListener(listener: PropertyChangeListener): Unit =
    disp.removeListener(listener)

  def getBackgroundHighlighter: BackgroundEditorHighlighter = null

  def getCurrentLocation: FileEditorLocation = null

  def dispose(): Unit = ()
}
