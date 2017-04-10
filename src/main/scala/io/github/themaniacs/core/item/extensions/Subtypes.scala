package io.github.themaniacs.core.item.extensions

trait Subtypes {
  // prevent implementation of multiple exclusive traits
  final def needsExclusiveSubtypes() = {}

  //sub item names
  val subItems: List[String]
}