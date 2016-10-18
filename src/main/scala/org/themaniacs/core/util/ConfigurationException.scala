package org.themaniacs.core.util

class ConfigurationException(msg: String, cause: Option[Throwable]) extends RuntimeException(msg, cause.orNull) {
  def this(msg: String) = this(msg, None)
}
