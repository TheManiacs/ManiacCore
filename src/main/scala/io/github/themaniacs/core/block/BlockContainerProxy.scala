package io.github.themaniacs.core.block

import io.github.themaniacs.core.block.extensions.TileEntity
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{Block, ITileEntityProvider}
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.world.World

class BlockContainerProxy(implementation: BlockBase with TileEntity) extends Block(implementation.material, implementation.mapColor) with BlockProxy with ITileEntityProvider {
  override def createNewTileEntity(worldIn: World, meta: Int): TE = implementation.makeNewTileEntity(worldIn, getStateFromMeta(meta))

  override val impl: BlockBase = implementation

  override def getDefaultBlockState: IBlockState = getDefaultState
  override def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
  override def getBaseBlockState: IBlockState = this.blockState.getBaseState
}