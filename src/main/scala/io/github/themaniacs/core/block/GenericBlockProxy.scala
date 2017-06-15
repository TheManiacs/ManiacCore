package io.github.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState

class GenericBlockProxy(override val impl: BlockBase) extends Block(impl.material, impl.mapColor) with BlockProxy {
  override def getDefaultBlockState: IBlockState = getDefaultState
  override def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
  override def getBaseBlockState: IBlockState = this.blockState.getBaseState
}