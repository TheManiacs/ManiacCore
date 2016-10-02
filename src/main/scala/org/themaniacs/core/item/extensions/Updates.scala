package org.themaniacs.core.item.extensions

import net.minecraft.entity.{EntityLivingBase, Entity}
import net.minecraft.item.ItemStack
import net.minecraft.world.World

trait Updates {
  //called every tick while in a player's inventory
  def onTick(stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean)

  //called every tick while being used by a player
  def onUseTick(stack: ItemStack, entity: EntityLivingBase, count: Int)
}
