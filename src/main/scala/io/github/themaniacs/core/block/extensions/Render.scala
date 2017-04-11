package io.github.themaniacs.core.block.extensions

import io.github.themaniacs.core.util.RenderType

//noinspection AccessorLikeMethodIsUnit
trait NoRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}

//noinspection AccessorLikeMethodIsUnit
trait ModelRender {
  final def canOnlyImplementOneRenderType(): Unit = ()

  val renderLayer: RenderType
  val isFullOpaqueCube: Boolean
}

//noinspection AccessorLikeMethodIsUnit
trait LiquidRender {
  final def canOnlyImplementOneRenderType(): Unit = ()

  val renderLayer: RenderType
  val isFullOpaqueCube: Boolean
}

//noinspection AccessorLikeMethodIsUnit
trait AnimatedRender {
  final def canOnlyImplementOneRenderType(): Unit = ()

  val renderLayer: RenderType
  val isFullOpaqueCube: Boolean
}
