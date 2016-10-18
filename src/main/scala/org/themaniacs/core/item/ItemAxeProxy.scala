package org.themaniacs.core.item

import net.minecraft.item.ItemAxe
import org.themaniacs.core.item.extensions.Axe

class ItemAxeProxy(val impl: ItemBase with Axe) extends ItemAxe(impl.material) with ItemProxy