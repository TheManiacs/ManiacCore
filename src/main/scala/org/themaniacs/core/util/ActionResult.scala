package org.themaniacs.core.util

abstract class ActionResult[T]
case class Success[T](result: T) extends ActionResult[T]
case class Pass[T](result: T) extends ActionResult[T]
case class Fail[T](result: T) extends ActionResult[T]