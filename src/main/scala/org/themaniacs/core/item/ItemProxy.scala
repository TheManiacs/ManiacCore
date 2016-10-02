package org.themaniacs.core.item

import net.minecraft.entity.{EntityLivingBase, Entity}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{EnumAction, ItemStack, Item}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumFacing, EnumActionResult, ActionResult, EnumHand}
import net.minecraft.world.World
import org.themaniacs.core.item.extensions._
import org.themaniacs.core.util.{Fail, Pass, Success}

class ItemProxy(val implementation: ItemBase) extends Item {
  override def setMaxStackSize(maxStackSize: Int): Item = {
    implementation.maxStackSize = maxStackSize
    this
  }

  override def getItemStackLimit: Int = { implementation.maxStackSize }

  //Subtypes
  override def getHasSubtypes: Boolean = {
    implementation match {
      case t: Subtypes => true
      case _ => false
    }
  }

  //Updates
  override def onUpdate(stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean) = {
    implementation match {
      case updates: Updates => updates.onTick(stack, world, entity, itemSlot, isSelected)
    }
  }

  override def onUsingTick(stack: ItemStack, entity: EntityLivingBase, count: Int) = {
    implementation match {
      case updates: Updates => updates.onUseTick(stack, entity, count)
    }
  }

  //Usable
  override def getMaxItemUseDuration(stack: ItemStack): Int = {
    implementation match {
      case usable: Usable => usable.getUseDuration(stack)
      case _ => 0
    }
  }

  override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = {
    implementation match {
      case usable: Usable =>
        usable.onUse(stack, world, player, hand) match {
          case Success(item) => new ActionResult(EnumActionResult.SUCCESS, item)
          case Pass(item) => new ActionResult(EnumActionResult.PASS, item)
          case Fail(item) => new ActionResult(EnumActionResult.FAIL, item)
        }
      case _ => new ActionResult(EnumActionResult.PASS, stack)
    }
  }

  override def onItemUseFinish (stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack = {
    implementation match {
      case usable: Usable => usable.onUseFinish(stack, world, entity)
      case _ => stack
    }
  }

  override def onItemUseFirst(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult = {
    implementation match {
      case usable: Usable =>
        usable.onUseFirst(stack, world, player, hand, pos, side, hitX, hitY, hitZ) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  override def onPlayerStoppedUsing(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int) = {
    implementation match {
      case usable: Usable => usable.onUseStop(stack, world, entity, timeLeft)
    }
  }

  override def getItemUseAction(stack: ItemStack): EnumAction = {
    implementation match {
      case usable: Usable => usable.useAction
      case _ => EnumAction.NONE
    }
  }

  //BlockInteraction
  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    implementation match {
      case blockInteraction: BlockInteraction =>
        blockInteraction.onBlockInteract(stack, player, world, pos, hand, facing, hitX, hitY, hitZ) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  //EntityInteraction
  override def itemInteractionForEntity(stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = {
    implementation match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityRightClick(stack, player, target, hand)
      case _ => false
    }
  }

  override def onLeftClickEntity(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean = {
    implementation match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityLeftClick(stack, player, entity)
      case _ => false
    }
  }
}
