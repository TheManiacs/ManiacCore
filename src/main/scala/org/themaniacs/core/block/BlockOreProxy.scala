package org.themaniacs.core.block

import net.minecraft.block.BlockOre
import org.themaniacs.core.block.extensions.Ore


class BlockOreProxy(override val impl: BlockBase with Ore) extends BlockOre(impl.mapColor) with BlockProxy