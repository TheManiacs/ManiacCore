package org.themaniacs.core.block

trait NoRender {
  final def canOnlyImplementOneRenderType() = ()
}

trait LiquidRender {
  final def canOnlyImplementOneRenderType() = ()
}

trait AnimatedRender {
  final def canOnlyImplementOneRenderType() = ()
}
