package org.themaniacs.core.proxy

import net.minecraftforge.fml.common.event.FMLInitializationEvent

class ServerProxy extends Proxy {
  override def init(event: FMLInitializationEvent): Unit ={
    super.init(event)

    //register server side GUI packet handler
  }
}
