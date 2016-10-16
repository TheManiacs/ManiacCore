package org.themaniacs.core.item.extensions

import net.minecraft.block.Block
import net.minecraft.item.Item

trait Tool {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy() = {}
  final def needsExclusiveSubtypes() = {}

  val entityDamage: Float
  val attackSpeed: Float
  val material: Item.ToolMaterial
  val effectiveBlocks: Set[Block]
}
