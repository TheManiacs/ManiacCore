package org.themaniacs.core

import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry
import org.themaniacs.core.block.extensions.{Ore, TileEntity}
import org.themaniacs.core.block.{BlockBase, BlockContainerProxy, BlockOreProxy, GenericBlockProxy}

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
}
