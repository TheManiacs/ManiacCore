package org.themaniacs.core.block

import net.minecraft.block.{Block, ITileEntityProvider}
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.world.World
import org.themaniacs.core.block.extensions.TileEntity

class BlockContainerProxy(implementation: BlockBase with TileEntity) extends Block(implementation.material, implementation.mapColor) with BlockProxy with ITileEntityProvider {

  override def createNewTileEntity(worldIn: World, meta: Int): TE = implementation.makeNewTileEntity(worldIn, getStateFromMeta(meta))

  override val impl: BlockBase = implementation
}