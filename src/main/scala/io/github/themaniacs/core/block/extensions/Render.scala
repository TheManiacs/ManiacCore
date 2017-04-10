package io.github.themaniacs.core.block.extensions

trait NoRender {
  final def canOnlyImplementOneRenderType() = ()
}

trait LiquidRender {
  final def canOnlyImplementOneRenderType() = ()
}

trait AnimatedRender {
  final def canOnlyImplementOneRenderType() = ()
}
