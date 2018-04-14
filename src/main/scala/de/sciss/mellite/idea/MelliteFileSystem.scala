package de.sciss.mellite.idea

import com.intellij.openapi.vfs.{VirtualFile, VirtualFileListener, VirtualFileSystem}

class MelliteFileSystem extends VirtualFileSystem {
  def getProtocol: String = ???

  def findFileByPath(path: String): VirtualFile = ???

  def refresh(asynchronous: Boolean): Unit = ???

  def refreshAndFindFileByPath(path: String): VirtualFile = ???

  def addVirtualFileListener(listener: VirtualFileListener): Unit = ???

  def removeVirtualFileListener(listener: VirtualFileListener): Unit = ???

  def deleteFile(requestor: scala.Any, vFile: VirtualFile): Unit = ???

  def moveFile(requestor: scala.Any, vFile: VirtualFile, newParent: VirtualFile): Unit = ???

  def renameFile(requestor: scala.Any, vFile: VirtualFile, newName: String): Unit = ???

  def createChildFile(requestor: scala.Any, vDir: VirtualFile, fileName: String): VirtualFile = ???

  def createChildDirectory(requestor: scala.Any, vDir: VirtualFile, dirName: String): VirtualFile = ???

  def copyFile(requestor: scala.Any, virtualFile: VirtualFile, newParent: VirtualFile, copyName: String): VirtualFile = ???

  def isReadOnly: Boolean = true
}
