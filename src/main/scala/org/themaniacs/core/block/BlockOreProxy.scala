package org.themaniacs.core.block

import net.minecraft.block.BlockOre
import org.themaniacs.core.block.extensions.Ore


class BlockOreProxy(implementation: BlockBase with Ore) extends BlockOre(implementation.mapColor) with BlockProxy {
  override val impl = implementation
}