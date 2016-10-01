package org.themaniacs.core.block.extensions

import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

trait TileEntity {
  def makeNewTileEntity(world: World, state: IBlockState): TE

  def getTileEntity(world: World, pos: BlockPos) = {
    Option(world.getTileEntity(pos)).getOrElse(makeNewTileEntity(world, pos, world.getBlockState(pos)))
  }
}
