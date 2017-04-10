package io.github.themaniacs.core.item

import net.minecraft.item.ItemPickaxe
import io.github.themaniacs.core.item.extensions.Pickaxe

class ItemPickaxeProxy(val impl: ItemBase with Pickaxe) extends ItemPickaxe(impl.material) with ItemProxy