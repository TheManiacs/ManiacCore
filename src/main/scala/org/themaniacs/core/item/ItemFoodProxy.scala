package org.themaniacs.core.item

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{ItemStack, ItemFood}
import net.minecraft.world.World
import org.themaniacs.core.item.extensions.Food

class ItemFoodProxy(val impl: ItemBase with Food) extends ItemFood(impl.healAmount, impl.saturation, impl.wolfFood) with ItemProxy {
  // special stuff for food and usage
  override def onFoodEaten(stack: ItemStack, world: World, player: EntityPlayer) =
    impl.onEaten(stack, world, player)

  // default use duration of food is 32
  override def getMaxItemUseDuration(stack: ItemStack): Int =
    impl.getUseDuration(stack)
}