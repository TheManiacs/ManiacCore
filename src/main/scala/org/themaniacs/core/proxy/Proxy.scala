package org.themaniacs.core.proxy

import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}

class Proxy {
  def preInit(event: FMLPreInitializationEvent): Unit = {
    //load config file & register blocks, items, ores & nuggets
  }

  def init(event: FMLInitializationEvent): Unit = {
    //register network packets, init mod integration & register recipes
  }

  def postInit(event: FMLPostInitializationEvent): Unit ={

  }
}