package de.sciss.mellite.idea

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

class MelliteFileType extends FileType {
  def getName             = "Mellite workspace"
  def getDescription      = "Mellite workspace"
  def getDefaultExtension = "mllt"

  def getIcon: Icon = AllIcons.FileTypes.Custom

  def isBinary  : Boolean = true
  def isReadOnly: Boolean = true

  def getCharset(file: VirtualFile, content: Array[Byte]): String = null
}
object MelliteFileType {
  val instance = new MelliteFileType
}