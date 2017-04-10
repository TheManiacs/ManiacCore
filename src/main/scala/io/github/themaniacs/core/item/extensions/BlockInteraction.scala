package io.github.themaniacs.core.item.extensions

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.{EnumHand, EnumFacing}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import io.github.themaniacs.core.util.{Coords3, ActionResult}

trait BlockInteraction {
  def onBlockInteract(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hit: Coords3[Float, Float, Float]): ActionResult[Unit]
}
