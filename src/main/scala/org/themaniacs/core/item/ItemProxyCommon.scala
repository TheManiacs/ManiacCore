package org.themaniacs.core.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{EntityLivingBase, Entity}
import net.minecraft.item.{EnumAction, ItemStack, Item}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{EnumFacing, EnumActionResult, ActionResult, EnumHand}
import net.minecraft.world.World
import org.themaniacs.core.item.extensions._
import org.themaniacs.core.util._
import java.util.{List => JavaList}

import scala.collection.convert.wrapAsScala._

object ItemProxyCommon {

  def getItemStackLimit(self: Item with ItemProxy): Int =
    self.containedItem.maxStackSize

  //Subtypes
  def getHasSubtypes(self: Item with ItemProxy): Boolean = {
    self.containedItem match {
      case t: Subtypes => true
      case _ => false
    }
  }

  def getSubItems(self: Item with ItemProxy, item: Item, tab: CreativeTabs, subItems: JavaList[ItemStack]) = {
    self.containedItem match {
      case t: Subtypes => t.subItems.foldLeft(0) {
        (count, name) => {
          // Minecraft item damage values are signed 16bit integers (from -32768 to 32767)
          if (count <= 32767) {
            subItems += new ItemStack(self, 1, count)
          } else if (count <= 65535) {
            subItems += new ItemStack(self, 1, 32767-count)
          } else {
            throw new DeveloperFuckedUpException("Registering more than 65535 sub items is NOT supported!")
          }

          count + 1
        }
      }
      case _ => subItems.add(new ItemStack(item))
    }
  }

  //Updates
  def onUpdate(self: Item with ItemProxy, stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean) = {
    self.containedItem match {
      case updates: Updates => updates.onTick(stack, world, entity, itemSlot, isSelected)
      case _ => ()
    }
  }

  def onUsingTick(self: Item with ItemProxy, stack: ItemStack, entity: EntityLivingBase, count: Int) = {
    self.containedItem match {
      case updates: Updates => updates.onUseTick(stack, entity, count)
      case _ => ()
    }
  }

  //Usable
  def getMaxItemUseDuration(self: Item with ItemProxy, stack: ItemStack): Int = {
    self.containedItem match {
      case usable: Usable => usable.getUseDuration(stack)
      case _ => 0
    }
  }

  def onItemRightClick(self: Item with ItemProxy, stack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = {
    self.containedItem match {
      case usable: Usable =>
        usable.onUse(stack, world, player, hand) match {
          case Success(item) => new ActionResult(EnumActionResult.SUCCESS, item)
          case Pass(item) => new ActionResult(EnumActionResult.PASS, item)
          case Fail(item) => new ActionResult(EnumActionResult.FAIL, item)
        }
      case _ => new ActionResult(EnumActionResult.PASS, stack)
    }
  }

  def onItemUseFinish(self: Item with ItemProxy, stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack = {
    self.containedItem match {
      case usable: Usable => usable.onUseFinish(stack, world, entity)
      case _ => stack
    }
  }

  def onItemUseFirst(self: Item with ItemProxy, stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult = {
    self.containedItem match {
      case usable: Usable =>
        usable.onUseFirst(stack, world, player, hand, pos, side, new Coords3(hitX, hitY, hitZ)) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  def onPlayerStoppedUsing(self: Item with ItemProxy, stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int) = {
    self.containedItem match {
      case usable: Usable => usable.onUseStop(stack, world, entity, timeLeft)
      case _ => ()
    }
  }

  def getItemUseAction(self: Item with ItemProxy, stack: ItemStack): EnumAction = {
    self.containedItem match {
      case usable: Usable => usable.useAction
      case _ => EnumAction.NONE
    }
  }

  //BlockInteraction
  def onItemUse(self: Item with ItemProxy, stack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    self.containedItem match {
      case blockInteraction: BlockInteraction =>
        blockInteraction.onBlockInteract(stack, player, world, pos, hand, facing, new Coords3(hitX, hitY, hitZ)) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  //EntityInteraction
  def itemInteractionForEntity(self: Item with ItemProxy, stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = {
    self.containedItem match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityRightClick(stack, player, target, hand)
      case _ => false
    }
  }

  def onLeftClickEntity(self: Item with ItemProxy, stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean = {
    self.containedItem match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityLeftClick(stack, player, entity)
      case _ => false
    }
  }
}
