package org.themaniacs.core.item.extensions

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait Food {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy() = {}
  final def needsExclusiveUsable() = {}

  val healAmount: Int
  val saturation: Float
  val wolfFood: Boolean

  def onEaten(stack: ItemStack, world: World, player: EntityPlayer)
  def getUseDuration(stack: ItemStack): Int = 32
}
