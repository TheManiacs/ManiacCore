package io.github.themaniacs.core.util

sealed trait RenderType
case object SOLID extends RenderType
case object TRANSPARENT extends RenderType
case object CUTOUT extends RenderType