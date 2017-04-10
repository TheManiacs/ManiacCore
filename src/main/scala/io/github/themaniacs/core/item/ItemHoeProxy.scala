package io.github.themaniacs.core.item

import io.github.themaniacs.core.item.extensions.Hoe
import net.minecraft.item.ItemHoe

class ItemHoeProxy(val impl: ItemBase with Hoe) extends ItemHoe(impl.material) with ItemProxy