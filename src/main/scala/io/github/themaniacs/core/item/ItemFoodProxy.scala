package io.github.themaniacs.core.item

import io.github.themaniacs.core.item.extensions.Food
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemFood, ItemStack}
import net.minecraft.util.math.BlockPos
import net.minecraft.util._
import net.minecraft.world.World

class ItemFoodProxy(val impl: ItemBase with Food) extends ItemFood(impl.healAmount, impl.saturation, impl.wolfFood) with ItemProxy {
  // special stuff for food and usage
  override def onFoodEaten(stack: ItemStack, world: World, player: EntityPlayer): Unit =
    impl.onEaten(stack, world, player)

  // default use duration of food is 32
  override def getMaxItemUseDuration(stack: ItemStack): Int =
    impl.getUseDuration(stack)

  //Usable - default implementation here as it would interfere with ItemFood
  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = {
    if(player.canEat(impl.alwaysEdible)) {
      player.setActiveHand(hand)
      new ActionResult(EnumActionResult.SUCCESS, itemStack)
    } else {
      new ActionResult(EnumActionResult.FAIL, itemStack)
    }
  }

  override def onItemUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack =
    stack

  override def onItemUseFirst(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult =
    EnumActionResult.PASS

  override def onPlayerStoppedUsing(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int): Unit = ()

  override def getItemUseAction(stack: ItemStack): EnumAction =
    EnumAction.NONE
}