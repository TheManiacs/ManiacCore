package io.github.themaniacs.core.item

import net.minecraft.item.ItemTool
import io.github.themaniacs.core.item.extensions.Tool

import scala.collection.JavaConverters._

class ItemToolProxy(val impl: ItemBase with Tool) extends ItemTool(impl.entityDamage, impl.attackSpeed, impl.material, impl.effectiveBlocks.asJava) with ItemProxy