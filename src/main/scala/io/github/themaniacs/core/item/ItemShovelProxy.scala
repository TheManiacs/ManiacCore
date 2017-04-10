package io.github.themaniacs.core.item

import net.minecraft.item.ItemSpade
import io.github.themaniacs.core.item.extensions.Shovel

class ItemShovelProxy(val impl: ItemBase with Shovel) extends ItemSpade(impl.material) with ItemProxy