package io.github.themaniacs.core.block.extensions

import net.minecraft.block.material.Material
import io.github.themaniacs.core.block.BlockBase

trait Ore extends BlockBase {
  // This is a hardcoded limitation of Minecraft
  final override val material = Material.ROCK

  final def youCanOnlyImplementOneTypeTrait(): Unit = {}
}
