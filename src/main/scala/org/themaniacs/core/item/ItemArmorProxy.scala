package org.themaniacs.core.item

import net.minecraft.item.ItemArmor
import org.themaniacs.core.item.extensions.Armor

class ItemArmorProxy(val impl: ItemBase with Armor) extends ItemArmor(impl.material, impl.renderIndex, impl.equipmentSlot) with ItemProxy