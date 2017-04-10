package io.github.themaniacs.core.block.extensions

//noinspection AccessorLikeMethodIsUnit
trait NoRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}

//noinspection AccessorLikeMethodIsUnit
trait ModelRender {
  final def canOnlyImplementOneRenderType(): Unit = ()

  val isOpaqueCube = true
}

//noinspection AccessorLikeMethodIsUnit
trait LiquidRender {
  final def canOnlyImplementOneRenderType(): Unit = ()
}

//noinspection AccessorLikeMethodIsUnit
trait AnimatedRender {
  final def canOnlyImplementOneRenderType(): Unit = ()

  val isOpaqueCube = true
}
