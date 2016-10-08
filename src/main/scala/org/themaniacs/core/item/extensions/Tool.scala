package org.themaniacs.core.item.extensions

import net.minecraft.block.Block
import net.minecraft.item.Item

trait Tool {
  val entityDamage: Float
  val attackSpeed: Float
  val material: Item.ToolMaterial
  val effectiveBlocks: Set[Block]
}
