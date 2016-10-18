package org.themaniacs.core.item

import net.minecraft.item.ItemSpade
import org.themaniacs.core.item.extensions.Shovel

class ItemShovelProxy(val impl: ItemBase with Shovel) extends ItemSpade(impl.material) with ItemProxy