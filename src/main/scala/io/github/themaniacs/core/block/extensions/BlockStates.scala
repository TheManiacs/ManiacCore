package io.github.themaniacs.core.block.extensions

import net.minecraft.block.state.{BlockStateContainer, IBlockState}
import net.minecraft.util.{Mirror, Rotation}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

trait BlockStates {
  def changeDefaultState(state: IBlockState): IBlockState = state
  def getStateForMeta(meta: Int): Option[IBlockState] = None
  def getMetaForState(state: IBlockState): Option[Int] = None
  def getStateForRotation(state: IBlockState, rotation: Rotation): IBlockState = state
  def getStateForMirror(state: IBlockState, mirror: Mirror): IBlockState = state
  def createBlockState(): Option[BlockStateContainer] = None
  def getExtendedState(state: IBlockState, world: IBlockAccess, pos: BlockPos): Option[IBlockState] = None
  def getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): Option[IBlockState] = None
}