package org.themaniacs.core

import org.themaniacs.core.block.extensions.TileEntity
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry
import org.themaniacs.core.block.extensions.Ore
import org.themaniacs.core.block.{BlockBase, BlockContainerProxy, BlockOreProxy, GenericBlockProxy}
import org.themaniacs.core.item.extensions._
import org.themaniacs.core.item._
import org.themaniacs.core.util.DeveloperFuckedUpException

object ManiacRegistry {
  def registerBlock(block: BlockBase) = {
    val blockProxy = block match {
      case b: BlockBase with TileEntity => new BlockContainerProxy(b)
      case b: BlockBase with Ore => new BlockOreProxy(b)
      case b => new GenericBlockProxy(b)
    }
    val mod: String = Loader.instance().activeModContainer().getModId
    blockProxy.setUnlocalizedName(mod + ".block." + block.id)
    blockProxy.setRegistryName(mod, block.id)
    block.creativeTab match {
      case Some(tab) => blockProxy.setCreativeTab(tab)
      case None => ()
    }
    GameRegistry.register(blockProxy)

    val itemBlock = block.makeItemBlock(blockProxy)
    itemBlock.setRegistryName(blockProxy.getRegistryName)
  }

  def registerItem(item: ItemBase) = {
    val itemProxy = item match {
      case i: ItemBase with Food => new ItemFoodProxy(i)
      case i: ItemBase with Tool => new ItemToolProxy(i)
      case i: ItemBase with Sword => new ItemSwordProxy(i)
      case i: ItemBase with Pickaxe => new ItemPickaxeProxy(i)
      case i: ItemBase with Axe => new ItemAxeProxy(i)
      case i: ItemBase with Shovel => new ItemShovelProxy(i)
      case i: ItemBase with Hoe => new ItemHoeProxy(i)
      case i: ItemBase with Armor => new ItemArmorProxy(i)
      case i => new ItemProxyBase(i)
    }
    val mod: String = Loader.instance().activeModContainer().getModId
    itemProxy.setUnlocalizedName(mod + ".item." + item.id)
    itemProxy.setRegistryName(mod, item.id)
    item.creativeTab match {
      case Some(tab) => itemProxy.setCreativeTab(tab)
      case None => ()
    }
    GameRegistry.register(itemProxy)

    item match {
      case i: ItemBase with Subtypes => i.subItems.foldLeft(0) {
        (count, name) => {
          // Minecraft item damage values are signed 16bit integers (from -32768 to 32767)
          if (count <= 32767) {
            ManiacCore.proxy.registerItemRender(itemProxy, count, Some(name))
          } else if (count <= 65535) {
            ManiacCore.proxy.registerItemRender(itemProxy, 32767 - count, Some(name))
          } else {
            throw new DeveloperFuckedUpException("Registering more than 65535 sub items is NOT supported!")
          }

          count + 1
        }
      }
      case _ => ManiacCore.proxy.registerItemRender(itemProxy, 0, None)
    }
  }
}