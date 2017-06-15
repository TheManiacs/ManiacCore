package io.github.themaniacs.core.block

import net.minecraft.block.Block
import net.minecraft.block.state.{BlockStateContainer, IBlockState}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{BlockRenderLayer, EnumBlockRenderType, Mirror, Rotation}
import net.minecraft.world.{IBlockAccess, World}
import io.github.themaniacs.core.block.extensions._
import io.github.themaniacs.core.util.{CUTOUT, DeveloperFuckedUpException, SOLID, TRANSPARENT}

trait BlockProxy extends Block {
  val impl: BlockBase

  override def onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack): Unit = {
    impl.onPlaced(worldIn, pos, None, None, state, Option(placer), Option(stack))
  }

  override def onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    impl.onPlaced(worldIn, pos, None, None, state, None, None)
  }

  override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
    impl.onBroken(worldIn, pos, state, None)
  }

  override def neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromBlock: BlockPos): Unit = {
    impl.onNeighbourChange(worldIn, state, pos, Option(fromBlock), Option(blockIn))
  }

  override def onNeighborChange(world: IBlockAccess, pos: BlockPos, neighbor: BlockPos): Unit = {
    impl.onNeighbourChange(world, world.getBlockState(pos), pos, Option(neighbor), None)
  }

  override def getRenderType(state: IBlockState): EnumBlockRenderType = {
    impl match {
      case _: NoRender => EnumBlockRenderType.INVISIBLE
      case _: ModelRender => EnumBlockRenderType.MODEL
      case _: LiquidRender => EnumBlockRenderType.LIQUID
      case _: AnimatedRender => EnumBlockRenderType.ENTITYBLOCK_ANIMATED
      case _ => throw new DeveloperFuckedUpException(s"No render trait implemented on ${impl.getClass.getName}. Fix plz. If you want the block to be invisible use NoRender.")
    }
  }

  override def getBlockLayer: BlockRenderLayer = {
    impl match {
      case _: NoRender => BlockRenderLayer.CUTOUT
      case i: ModelRender => i.renderLayer match {
        case SOLID => BlockRenderLayer.SOLID
        case TRANSPARENT => BlockRenderLayer.TRANSLUCENT
        case CUTOUT => BlockRenderLayer.CUTOUT
      }
      case i: LiquidRender => i.renderLayer match {
        case SOLID => BlockRenderLayer.SOLID
        case TRANSPARENT => BlockRenderLayer.TRANSLUCENT
        case CUTOUT => BlockRenderLayer.CUTOUT
      }
      case i: AnimatedRender => i.renderLayer match {
        case SOLID => BlockRenderLayer.SOLID
        case TRANSPARENT => BlockRenderLayer.TRANSLUCENT
        case CUTOUT => BlockRenderLayer.CUTOUT
      }
    }
  }

  override def canRenderInLayer(state: IBlockState, layer: BlockRenderLayer): Boolean = {
    impl match {
      case _: NoRender => false
      case i: ModelRender => i.renderLayer match {
        case SOLID => layer == BlockRenderLayer.SOLID
        case TRANSPARENT => layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT
        case CUTOUT => layer == BlockRenderLayer.CUTOUT
      }
      case i: LiquidRender => i.renderLayer match {
        case SOLID => layer == BlockRenderLayer.SOLID
        case TRANSPARENT => layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT
        case CUTOUT => layer == BlockRenderLayer.CUTOUT
      }
      case i: AnimatedRender => i.renderLayer match {
        case SOLID => layer == BlockRenderLayer.SOLID
        case TRANSPARENT => layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT
        case CUTOUT => layer == BlockRenderLayer.CUTOUT
      }
    }
  }

  override def isFullCube(state: IBlockState): Boolean = isOpaqueCube(state)

  override def isOpaqueCube(state: IBlockState): Boolean = {
    impl match {
      case _: NoRender => false
      case i: ModelRender => i.isFullOpaqueCube
      case i: LiquidRender =>  i.isFullOpaqueCube
      case i: AnimatedRender => i.isFullOpaqueCube
      case null => true // Workaround for Minecraft being fucky
      case _ => throw new DeveloperFuckedUpException(s"No render trait implemented on ${impl.getClass.getName}. Fix plz. If you want the block to be invisible use NoRender.")
    }
  }

  override def getStateFromMeta(meta: Int): IBlockState = {
    impl match {
      case i: BlockStates => i.getStateForMeta(meta) match {
        case Some(state) => state
        case None => super.getStateFromMeta(meta)
      }
      case _ => super.getStateFromMeta(meta)
    }
  }

  override def getMetaFromState(state: IBlockState): Int = {
    impl match {
      case i: BlockStates => i.getMetaForState(state) match {
        case Some(meta) => meta
        case None => super.getMetaFromState(state)
      }
      case _ => super.getMetaFromState(state)
    }
  }

  override def withRotation(state: IBlockState, rot: Rotation): IBlockState = {
    impl match {
      case i: BlockStates => i.getStateForRotation(state, rot)
      case _ => super.withRotation(state, rot)
    }
  }

  override def withMirror(state: IBlockState, mirror: Mirror): IBlockState = {
    impl match {
      case i: BlockStates => i.getStateForMirror(state, mirror)
      case _ => super.withMirror(state, mirror)
    }
  }

  override def createBlockState(): BlockStateContainer = {
    impl match {
      case i: BlockStates => i.createBlockState() match {
        case Some(state) => state
        case None => super.createBlockState()
      }
      case _ => super.createBlockState()
    }
  }

  override def getExtendedState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState = {
    impl match {
      case i: BlockStates => i.getExtendedState(state, world, pos) match {
        case Some(state) => state
        case None => super.getExtendedState(state, world, pos)
      }
      case _ => super.getExtendedState(state, world, pos)
    }
  }

  override def getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState = {
    impl match {
      case i: BlockStates => i.getActualState(state, world, pos) match {
        case Some(state) => state
        case None => super.getActualState(state, world, pos)
      }
      case _ => super.getActualState(state, world, pos)
    }
  }

  def getDefaultBlockState: IBlockState = getDefaultState
  def setDefaultBlockState(state: IBlockState): Unit = setDefaultState(state)
}