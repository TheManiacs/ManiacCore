package org.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{IBlockAccess, World}

trait BlockProxy extends Block {
  val impl: BlockBase
  override def onBlockPlaced(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState = {
    val state = getStateFromMeta(meta)
    impl.onPlaced(worldIn, pos, Option(facing), Some((hitX, hitY, hitZ)), state, Option(placer), None)
    state
  }

  override def onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack) = {
    impl.onPlaced(worldIn, pos, None, None, state, Option(placer), Option(stack))
  }

  override def onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) = {
    impl.onPlaced(worldIn, pos, None, None, state, None, None)
  }

  override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) = {
    impl.onBroken(worldIn, pos, state, None)
  }

  override def neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block) = {
    impl.onNeighbourChange(worldIn, state, pos, None, Option(blockIn))
  }

  override def onNeighborChange(world: IBlockAccess, pos: BlockPos, neighbor: BlockPos) = {
    impl.onNeighbourChange(world, world.getBlockState(pos), pos, Option(neighbor), None)
  }

  override def isFullCube(state: IBlockState) = true

  override def isOpaqueCube(state: IBlockState) = true
}
