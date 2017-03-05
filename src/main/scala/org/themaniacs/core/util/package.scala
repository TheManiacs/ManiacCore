package org.themaniacs.core

import java.io.File

package object util {

  def file(path: String) = new File(path)

  implicit class FileExtensions(wrapped: File) {
    def /(str: String) = new File(wrapped, str)
  }
}
