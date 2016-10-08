package org.themaniacs.core.item.extensions

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait Food {
  // the trait "Usable" will NOT work in combination with "Food"!
  val healAmount: Int
  val saturation: Float
  val wolfFood: Boolean

  def onEaten(stack: ItemStack, world: World, player: EntityPlayer)
  def getUseDuration(stack: ItemStack): Int = 32
}
