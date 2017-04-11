package io.github.themaniacs.core.item

import io.github.themaniacs.core.item.extensions.{EntityInteraction, Subtypes, Updates}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.item.{EnumAction, Item, ItemStack}
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{ActionResult, EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World
import io.github.themaniacs.core.item.extensions._
import io.github.themaniacs.core.util._
import java.util.{List => JavaList}

import scala.collection.convert.wrapAsScala._

trait ItemProxy extends Item {
  val impl: ItemBase

  override def getItemStackLimit: Int =
    impl.maxStackSize

  //Subtypes
  override def getHasSubtypes: Boolean = {
    impl match {
      case t: Subtypes => true
      case _ => false
    }
  }

  override def getSubItems(item: Item, tab: CreativeTabs, subItems: JavaList[ItemStack]): Unit = {
    impl match {
      case t: Subtypes => t.subItems.foldLeft(0) {
        (count, name) => {
          // Minecraft item damage values are signed 16bit integers (from -32768 to 32767)
          if (count <= 32767) {
            subItems += new ItemStack(this, 1, count)
          } else if (count <= 65535) {
            subItems += new ItemStack(this, 1, 32767-count)
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
  override def onUpdate(stack: ItemStack, world: World, entity: Entity, itemSlot: Int, isSelected: Boolean): Unit = {
    impl match {
      case updates: Updates => updates.onTick(stack, world, entity, itemSlot, isSelected)
      case _ => ()
    }
  }

  override def onUsingTick(stack: ItemStack, entity: EntityLivingBase, count: Int): Unit = {
    impl match {
      case updates: Updates => updates.onUseTick(stack, entity, count)
      case _ => ()
    }
  }

  //Usable
  override def getMaxItemUseDuration(stack: ItemStack): Int = {
    impl match {
      case usable: Usable => usable.getUseDuration(stack)
      case _ => 0
    }
  }

  override def onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = {
    impl match {
      case usable: Usable =>
        usable.onUse(itemStack, world, player, hand) match {
          case Success(item) => new ActionResult(EnumActionResult.SUCCESS, item)
          case Pass(item) => new ActionResult(EnumActionResult.PASS, item)
          case Fail(item) => new ActionResult(EnumActionResult.FAIL, item)
        }
      case _ => new ActionResult(EnumActionResult.PASS, itemStack)
    }
  }

  override def onItemUseFinish(stack: ItemStack, world: World, entity: EntityLivingBase): ItemStack = {
    impl match {
      case usable: Usable => usable.onUseFinish(stack, world, entity)
      case _ => stack
    }
  }

  override def onItemUseFirst(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, hand: EnumHand): EnumActionResult = {
    impl match {
      case usable: Usable =>
        usable.onUseFirst(itemStack, world, player, hand, pos, side, new Coords3(hitX, hitY, hitZ)) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  override def onPlayerStoppedUsing(stack: ItemStack, world: World, entity: EntityLivingBase, timeLeft: Int): Unit = {
    impl match {
      case usable: Usable => usable.onUseStop(stack, world, entity, timeLeft)
      case _ => ()
    }
  }

  override def getItemUseAction(stack: ItemStack): EnumAction = {
    impl match {
      case usable: Usable => usable.useAction
      case _ => EnumAction.NONE
    }
  }

  //BlockInteraction
  override def onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult = {
    impl match {
      case blockInteraction: BlockInteraction =>
        blockInteraction.onBlockInteract(itemStack, player, world, pos, hand, facing, new Coords3(hitX, hitY, hitZ)) match {
          case Success(_) => EnumActionResult.SUCCESS
          case Pass(_) => EnumActionResult.PASS
          case Fail(_) => EnumActionResult.FAIL
        }
      case _ => EnumActionResult.PASS
    }
  }

  //EntityInteraction
  override def itemInteractionForEntity(stack: ItemStack, player: EntityPlayer, target: EntityLivingBase, hand: EnumHand): Boolean = {
    impl match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityRightClick(stack, player, target, hand)
      case _ => false
    }
  }

  override def onLeftClickEntity(stack: ItemStack, player: EntityPlayer, entity: Entity): Boolean = {
    impl match {
      case entityInteraction: EntityInteraction => entityInteraction.onEntityLeftClick(stack, player, entity)
      case _ => false
    }
  }
}
