package org.themaniacs.core.proxy

import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}

class ClientProxy extends Proxy{
  override def init(event: FMLInitializationEvent) ={
    super.init(event)

    //register renders, client network packets, custom keybinds und client events
  }
}
