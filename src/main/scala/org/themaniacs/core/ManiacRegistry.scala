package org.themaniacs.core

import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.registry.GameRegistry
import org.themaniacs.core.item.extensions.{Tool, Food, Subtypes}
import org.themaniacs.core.item.{ItemToolProxy, ItemFoodProxy, ItemProxyBase, ItemBase}
import org.themaniacs.core.util.DeveloperFuckedUpException

object ManiacRegistry {
  def registerItem(item: ItemBase) = {
    val itemProxy = item match {
      case i: ItemBase with Food => new ItemFoodProxy(i)
      case i: ItemBase with Tool => new ItemToolProxy(i)
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
      case i: ItemBase with Subtypes => i.subItems.foldLeft(0){
        (count, name) => {
          if(count <= 65534) {
            ManiacCore.proxy.registerItemRender(itemProxy, count, Some(name))
          }else{
            throw new DeveloperFuckedUpException("Registering more than 65535 sub items is NOT supported!")
          }

          count+1
        }
      }
      case _ => ManiacCore.proxy.registerItemRender(itemProxy, 0, None)
    }
  }
}