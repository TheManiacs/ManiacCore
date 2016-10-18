package org.themaniacs.core.item

import net.minecraft.item.ItemSword
import org.themaniacs.core.item.extensions.Sword

class ItemSwordProxy(val impl: ItemBase with Sword) extends ItemSword(impl.material) with ItemProxy