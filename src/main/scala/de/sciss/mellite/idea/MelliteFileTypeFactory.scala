package de.sciss.mellite.idea

import com.intellij.openapi.fileTypes.{FileTypeConsumer, FileTypeFactory}

class MelliteFileTypeFactory extends FileTypeFactory {
  def createFileTypes(consumer: FileTypeConsumer): Unit = {
    val tpe = MelliteFileType.instance
    consumer.consume(tpe, tpe.getDefaultExtension)
  }
}
