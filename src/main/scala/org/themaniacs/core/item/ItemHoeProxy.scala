package org.themaniacs.core.item

import net.minecraft.item.ItemHoe
import org.themaniacs.core.item.extensions.Hoe

class ItemHoeProxy(val impl: ItemBase with Hoe) extends ItemHoe(impl.material) with ItemProxy