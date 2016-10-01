package org.themaniacs.core

import proxy.{Proxy => CommonProxy}
import net.minecraftforge.fml.common.{SidedProxy, Mod}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j
import org.apache.logging.log4j.LogManager

@Mod(modid = ManiacCore.ModID, name = ManiacCore.Name, version = ManiacCore.Version, modLanguage = "scala" /*@MCVERSIONDEP@*/)
object ManiacCore {

  final val ModID = "maniaccore"

  final val Name = "ManiacCore"

  final val Version = "@VERSION@"

  def log = logger.getOrElse(LogManager.getLogger(Name))

  var logger: Option[log4j.Logger] = None


  @SidedProxy(clientSide = "org.themaniacs.core.proxy.ClientProxy", serverSide = "org.themaniacs.core.proxy.ServerProxy")
  var proxy: CommonProxy = null

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
    logger = Option(e.getModLog)
    proxy.preInit(e)
    ManiacCore.log.info("Done with pre init phase.")
  }

  @EventHandler
  def init(e: FMLInitializationEvent) = {
    proxy.init(e)
    ManiacCore.log.info("Done with init phase.")
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) = {
    proxy.postInit(e)
    ManiacCore.log.info("Done with post init phase.")
  }

}