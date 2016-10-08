package org.themaniacs.core.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{EntityLivingBase, Entity}
import net.minecraft.item._
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumActionResult, EnumFacing, ActionResult, EnumHand}
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.{Side, SideOnly}
import java.util.{List => JavaList}

import org.themaniacs.core.item.extensions.Hoe

class ItemHoeProxy(val containedItem: ItemBase with Hoe) extends ItemHoe(containedItem.material) with ItemProxy {
  override def getItemStackLimit: Int = ItemProxyCommon.getItemStackLimit(this)

  //Subtypes
  override def getHasSubtypes: Boolean = ItemProxyCommon.getHasSubtypes(this)
  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tab: CreativeTabs, subItems: JavaList[ItemStack]) = ItemProxyCommon.getSubItems(this, item, tab, subItems)

  //Updates
  override def onUpdate(stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean) = ItemProxyCommon.onUpdate(this, stack, world, entity, itemSlot, isSelected)
  override def onUsingTick(stack: ItemStack, entity: EntityLivingBase, count: Int) = ItemProxyCommon.onUsingTick(this, stack, entity, count)

  //Usable
  override def getMaxItemUseDuration(stack: ItemStack): Int = ItemProxyCommon.getMaxItemUseDuration(this, stack)
  override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = ItemProxyCommon.onItemRightClick(this, stack, world, player, hand)
  override def onItemUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack = ItemProxyCommon.onItemUseFinish(this, stack, world, entity)
  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult = ItemProxyCommon.onItemUseFirst(this, stack, player ,world, pos, side, hitX, hitY, hitZ, hand)
  override def onPlayerStoppedUsing(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int) = ItemProxyCommon.onPlayerStoppedUsing(this, stack, world, entity, timeLeft)
  override def getItemUseAction(stack: ItemStack): EnumAction = ItemProxyCommon.getItemUseAction(this, stack)

  //BlockInteraction
  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = ItemProxyCommon.onItemUse(this, stack, player, world, pos, hand, facing, hitX, hitY, hitZ)

  //EntityInteraction
  override def itemInteractionForEntity(stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = ItemProxyCommon.itemInteractionForEntity(this, stack, player, target, hand)
  override def onLeftClickEntity(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean = ItemProxyCommon.onLeftClickEntity(this, stack, player, entity)
}
