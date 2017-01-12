package org.themaniacs.core.block.extensions

import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

trait TileEntity {
  def makeNewTileEntity(world: World, state: IBlockState): TE

  def getTileEntity(world: World, pos: BlockPos): TE = {
    Option(world.getTileEntity(pos)).getOrElse(makeNewTileEntity(world, world.getBlockState(pos)))
  }

  final def youCanOnlyImplementOneTypeTrait(): Unit = {}
}
