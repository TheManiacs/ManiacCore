package org.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.material.{MapColor, Material}
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

abstract class BlockBase {
  def id: String
  def material: Material
  def mapColor: MapColor

  def onPlaced(world: IBlockAccess, pos: BlockPos, facing: Option[EnumFacing], subPos: Option[(Float, Float, Float)], state: IBlockState, source: Option[EntityLivingBase], stack: Option[ItemStack]) = ()
  def onBroken(world: IBlockAccess, pos: BlockPos, state: IBlockState, player: Option[EntityPlayer]) = ()
  def onNeighbourChange(world: IBlockAccess, state: IBlockState, myPos: BlockPos, otherPos: Option[BlockPos], otherBlock: Option[Block]) = ()

  def makeItemBlock(proxy: BlockProxy) = new GeneralItemBlock(proxy)
}
