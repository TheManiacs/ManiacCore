package org.themaniacs.core.item.extensions

import net.minecraft.item.Item

trait Axe {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy(): Unit = {}
  final def needsExclusiveSubtypes(): Unit = {}

  val material: Item.ToolMaterial
}
