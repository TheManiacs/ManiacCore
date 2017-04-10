package io.github.themaniacs.core.item.extensions

import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumHand

trait EntityInteraction {
  //called when right-clicking an entity.
  def onEntityRightClick(stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean

  //called when attacking an entity. when returning true any further actions (dealing damage, ...) will be canceled
  def onEntityLeftClick(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean
}