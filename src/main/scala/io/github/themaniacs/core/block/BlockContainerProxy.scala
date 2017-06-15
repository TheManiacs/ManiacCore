package io.github.themaniacs.core.block

import io.github.themaniacs.core.block.extensions.TileEntity
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{Block, ITileEntityProvider}
import net.minecraft.tileentity.{TileEntity => TE}
import net.minecraft.world.World

class BlockContainerProxy(override val impl: BlockBase with TileEntity) extends Block(impl.material, impl.mapColor) with BlockProxy with ITileEntityProvider {
  override def createNewTileEntity(worldIn: World, meta: Int): TE = impl.makeNewTileEntity(worldIn, getStateFromMeta(meta))

  override def getDefaultBlockState: IBlockState = getDefaultState
  override def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
  override def getBaseBlockState: IBlockState = this.blockState.getBaseState
}