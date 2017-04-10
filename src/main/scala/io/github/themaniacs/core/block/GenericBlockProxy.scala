package io.github.themaniacs.core.block

import net.minecraft.block.Block

class GenericBlockProxy(override val impl: BlockBase) extends Block(impl.material, impl.mapColor) with BlockProxy
