package io.github.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.material.{MapColor, Material}
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{EnumBlockRenderType, EnumFacing}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

abstract class BlockBase {
  val id: String
  val material: Material
  val mapColor: MapColor
  val creativeTab: Option[CreativeTabs]

  def onPlaced(world: IBlockAccess, pos: BlockPos, facing: Option[EnumFacing], subPos: Option[(Float, Float, Float)], state: IBlockState, source: Option[EntityLivingBase], stack: Option[ItemStack]): Unit = ()
  def onBroken(world: IBlockAccess, pos: BlockPos, state: IBlockState, player: Option[EntityPlayer]): Unit = ()
  def onNeighbourChange(world: IBlockAccess, state: IBlockState, myPos: BlockPos, otherPos: Option[BlockPos], otherBlock: Option[Block]): Unit = ()

  def makeItemBlock(proxy: BlockProxy) = new GeneralItemBlock(proxy)
  def getRenderType(state: IBlockState): EnumBlockRenderType = EnumBlockRenderType.MODEL
}
