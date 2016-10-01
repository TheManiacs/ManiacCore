package org.themaniacs.core.proxy

import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

trait Proxy {
  def preInit(event: FMLPreInitializationEvent) = {
    //load config file & register blocks, items, ores & nuggets
  }

  def init(event: FMLInitializationEvent) = {
    //register network packets, init mod integration & register recipes
  }

  def postInit(event: FMLPostInitializationEvent) ={

  }
}
