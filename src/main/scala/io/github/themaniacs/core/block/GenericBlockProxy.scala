package io.github.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState

class GenericBlockProxy(override val impl: BlockBase) extends Block(impl.material, impl.mapColor) with BlockProxy {
  def getDefaultBlockState: IBlockState = getDefaultState
  def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
}