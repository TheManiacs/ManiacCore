package org.themaniacs.core.proxy

import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.event.FMLInitializationEvent

class ClientProxy extends Proxy{
  override def init(event: FMLInitializationEvent): Unit ={
    super.init(event)

    //register renders, client network packets, custom keybinds und client events
  }

  override def registerItemRender(item: Item, damage: Int, name: Option[String]): Unit ={
    val modelResource = name match {
      case Some(n) => new ResourceLocation(item.getRegistryName.toString + "." + n)
      case None => item.getRegistryName
    }
    ModelLoader.setCustomModelResourceLocation(item, damage, new ModelResourceLocation(modelResource, "inventory"))
  }
}
