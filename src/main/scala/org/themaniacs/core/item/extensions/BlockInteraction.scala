package org.themaniacs.core.item.extensions

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{EnumHand, EnumFacing}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.themaniacs.core.util.ActionResult

trait BlockInteraction {
  def onBlockInteract(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): ActionResult[_]
}
