package io.github.themaniacs.core.item.extensions

import net.minecraft.item.Item

trait Pickaxe {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy() = {}
  final def needsExclusiveSubtypes() = {}

  val material: Item.ToolMaterial
}
