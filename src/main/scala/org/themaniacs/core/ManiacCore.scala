package org.themaniacs.core

import proxy.{Proxy => CommonProxy}
import net.minecraftforge.fml.common.{Mod, SidedProxy}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j
import org.apache.logging.log4j.{LogManager, Logger}

@Mod(modid = ManiacCore.ModID, name = ManiacCore.Name, version = ManiacCore.Version, modLanguage = "scala" /*@MCVERSIONDEP@*/)
object ManiacCore {

  final val ModID = "maniaccore"

  final val Name = "ManiacCore"

  final val Version = "@VERSION@"

  def log: Logger = logger.getOrElse(LogManager.getLogger(Name))

  var logger: Option[log4j.Logger] = None


  @SidedProxy(clientSide = "org.themaniacs.core.proxy.ClientProxy", serverSide = "org.themaniacs.core.proxy.ServerProxy")
  var proxy: CommonProxy = _

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
    logger = Option(e.getModLog)
    proxy.preInit(e)
    ManiacCore.log.info("Done with pre init phase.")
  }

  @EventHandler
  def init(e: FMLInitializationEvent): Unit = {
    proxy.init(e)
    ManiacCore.log.info("Done with init phase.")
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent): Unit = {
    proxy.postInit(e)
    ManiacCore.log.info("Done with post init phase.")
  }

}