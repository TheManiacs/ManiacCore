package io.github.themaniacs.core.item.extensions

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemStack}
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import io.github.themaniacs.core.util.{Coords3, ActionResult}

trait Usable {
  // prevent implementation of multiple exclusive traits
  final def needsExclusiveUsable() = {}

  val useAction: EnumAction

  def getUseDuration(stack: ItemStack): Int

  // called before interacting with blocks
  def onUseFirst(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand, pos: BlockPos, side: EnumFacing, hit: Coords3[Float, Float, Float]): ActionResult[Unit]

  // called when item gets right-clicked
  def onUse(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack]

  // called when finished using the item (when useDuration is over)
  def onUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack

  // called when stopped using the item
  def onUseStop(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int)
}
