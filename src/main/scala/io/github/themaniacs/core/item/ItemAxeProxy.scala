package io.github.themaniacs.core.item

import net.minecraft.item.ItemAxe
import io.github.themaniacs.core.item.extensions.Axe

class ItemAxeProxy(val impl: ItemBase with Axe) extends ItemAxe(impl.material) with ItemProxy