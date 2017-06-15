package io.github.themaniacs.core.block

import io.github.themaniacs.core.block.extensions.Ore
import net.minecraft.block.BlockOre
import net.minecraft.block.state.IBlockState

class BlockOreProxy(override val impl: BlockBase with Ore) extends BlockOre(impl.mapColor) with BlockProxy {
  override def getDefaultBlockState: IBlockState = getDefaultState
  override def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
  override def getBaseBlockState: IBlockState = this.blockState.getBaseState
}
