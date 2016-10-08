package org.themaniacs.core.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.{EntityLivingBase, Entity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack, ItemFood}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumFacing, EnumActionResult, EnumHand}
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.{Side, SideOnly}
import org.themaniacs.core.item.extensions._
import java.util.{List => JavaList}

class ItemFoodProxy(val containedItem: ItemBase with Food) extends ItemFood(containedItem.healAmount, containedItem.saturation, containedItem.wolfFood) with ItemProxy {
  // special stuff for food and usage
  override def onFoodEaten(stack: ItemStack, world: World, player: EntityPlayer) = {
    containedItem.onEaten(stack, world, player)
  }

  // default use duration of food is 32
  override def getMaxItemUseDuration(stack: ItemStack): Int = {
    containedItem.getUseDuration(stack)
  }

  override def getItemStackLimit: Int = ItemProxyCommon.getItemStackLimit(this)

  //Subtypes
  override def getHasSubtypes: Boolean = ItemProxyCommon.getHasSubtypes(this)
  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tab: CreativeTabs, subItems: JavaList[ItemStack]) = ItemProxyCommon.getSubItems(this, item, tab, subItems)

  //Updates
  override def onUpdate(stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean) = ItemProxyCommon.onUpdate(this, stack, world, entity, itemSlot, isSelected)
  override def onUsingTick(stack: ItemStack, entity: EntityLivingBase, count: Int) = ItemProxyCommon.onUsingTick(this, stack, entity, count)

  //Usable - not implemented here as it would interfere with ItemFood

  //BlockInteraction
  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = ItemProxyCommon.onItemUse(this, stack, player, world, pos, hand, facing, hitX, hitY, hitZ)

  //EntityInteraction
  override def itemInteractionForEntity(stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = ItemProxyCommon.itemInteractionForEntity(this, stack, player, target, hand)
  override def onLeftClickEntity(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean = ItemProxyCommon.onLeftClickEntity(this, stack, player, entity)
}
