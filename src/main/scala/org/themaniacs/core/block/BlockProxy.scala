package org.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.EnumBlockRenderType
import net.minecraft.world.{IBlockAccess, World}
import org.themaniacs.core.block.extensions.{AnimatedRender, LiquidRender, NoRender}

trait BlockProxy extends Block {
  val impl: BlockBase

  override def onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack): Unit = {
    impl.onPlaced(worldIn, pos, None, None, state, Option(placer), Option(stack))
  }

  override def onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    impl.onPlaced(worldIn, pos, None, None, state, None, None)
  }

  override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    impl.onBroken(worldIn, pos, state, None)
  }

  override def neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromBlock: BlockPos): Unit = {
    impl.onNeighbourChange(worldIn, state, pos, None, Option(blockIn))
  }

  override def onNeighborChange(world: IBlockAccess, pos: BlockPos, neighbor: BlockPos): Unit = {
    impl.onNeighbourChange(world, world.getBlockState(pos), pos, Option(neighbor), None)
  }

  override def getRenderType(state: IBlockState): EnumBlockRenderType = {
    impl match {
      case _: NoRender => EnumBlockRenderType.INVISIBLE
      case _: LiquidRender => EnumBlockRenderType.LIQUID
      case _: AnimatedRender => EnumBlockRenderType.ENTITYBLOCK_ANIMATED
      case _ => EnumBlockRenderType.MODEL
    }
  }

  override def isFullCube(state: IBlockState) = true

  override def isOpaqueCube(state: IBlockState) = true
}
