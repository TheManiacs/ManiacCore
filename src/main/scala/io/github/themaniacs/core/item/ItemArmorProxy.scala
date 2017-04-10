package io.github.themaniacs.core.item

import net.minecraft.item.ItemArmor
import io.github.themaniacs.core.item.extensions.Armor

class ItemArmorProxy(val impl: ItemBase with Armor) extends ItemArmor(impl.material, impl.renderIndex, impl.equipmentSlot) with ItemProxy