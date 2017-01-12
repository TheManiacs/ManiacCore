package org.themaniacs.core.block.extensions

//noinspection AccessorLikeMethodIsUnit
trait NoRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}

//noinspection AccessorLikeMethodIsUnit
trait LiquidRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}

//noinspection AccessorLikeMethodIsUnit
trait AnimatedRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}
