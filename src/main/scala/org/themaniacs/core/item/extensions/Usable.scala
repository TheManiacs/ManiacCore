package org.themaniacs.core.item.extensions

import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemStack}
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.themaniacs.core.util.ActionResult

trait Usable {
  val useAction: EnumAction

  def getUseDuration(stack: ItemStack): Int

  //called before interacting with blocks
  def onUseFirst(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): ActionResult[_]

  //called when item gets right-clicked
  def onUse(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack]

  //called when finished using the item (when useDuration is over)
  def onUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack

  //called when stopped using the item
  def onUseStop(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int)
}