package org.themaniacs.core.block

import net.minecraft.block.ITileEntityProvider
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.world.World
import org.themaniacs.core.block.extensions.TileEntity

class BlockContainerProxy(implementation: BlockBase with TileEntity) extends BlockProxy(implementation) with ITileEntityProvider {
  override def createNewTileEntity(worldIn: World, meta: Int): TE = implementation.makeNewTileEntity(worldIn, getStateFromMeta(meta))
}
