package io.github.themaniacs.core.block

import io.github.themaniacs.core.block.extensions.Ore
import net.minecraft.block.BlockOre

class BlockOreProxy(override val impl: BlockBase with Ore) extends BlockOre(impl.mapColor) with BlockProxy