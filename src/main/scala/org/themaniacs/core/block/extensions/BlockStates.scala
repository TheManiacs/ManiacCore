package org.themaniacs.core.block.extensions

import net.minecraft.block.state.IBlockState

trait BlockStates {
  def getStateForMeta(meta: Int): IBlockState

}
