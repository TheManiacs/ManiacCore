package org.themaniacs.core

import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry
import org.themaniacs.core.block.{BlockBase, BlockContainerProxy, BlockProxy}
import org.themaniacs.core.block.extensions.TileEntity

object ManiacRegistry {
  def registerBlock(block: BlockBase) = {
    val blockProxy = block match {
      case b: BlockBase with TileEntity => new BlockContainerProxy(b)
      case b => new BlockProxy(b)
    }
    val mod: String = Loader.instance().activeModContainer().getModId
    blockProxy.setUnlocalizedName(mod + ".block." + block.id)
    blockProxy.setRegistryName(mod, block.id)
    GameRegistry.register(blockProxy)

    val itemBlock = block.makeItemBlock(blockProxy)
    itemBlock.setRegistryName(blockProxy.getRegistryName)
  }
}
