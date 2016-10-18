package org.themaniacs.core.item

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemStack, ItemFood}
import net.minecraft.util.math.BlockPos
import net.minecraft.util._
import net.minecraft.world.World
import org.themaniacs.core.item.extensions.{Usable, Food}
import org.themaniacs.core.util.{Coords3, Fail, Pass, Success}

class ItemFoodProxy(val impl: ItemBase with Food) extends ItemFood(impl.healAmount, impl.saturation, impl.wolfFood) with ItemProxy {
  // special stuff for food and usage
  override def onFoodEaten(stack: ItemStack, world: World, player: EntityPlayer) =
    impl.onEaten(stack, world, player)

  // default use duration of food is 32
  override def getMaxItemUseDuration(stack: ItemStack): Int =
    impl.getUseDuration(stack)

  //Usable - default implementation here as it would interfere with ItemFood
  override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] =
    new ActionResult(EnumActionResult.PASS, stack)

  override def onItemUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack =
    stack

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult =
    EnumActionResult.PASS

  override def onPlayerStoppedUsing(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int) = ()

  override def getItemUseAction(stack: ItemStack): EnumAction =
    EnumAction.NONE
}