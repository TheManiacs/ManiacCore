package io.github.themaniacs.core.item

import io.github.themaniacs.core.item.extensions.Armor
import net.minecraft.item.ItemArmor

class ItemArmorProxy(val impl: ItemBase with Armor) extends ItemArmor(impl.material, impl.renderIndex, impl.equipmentSlot) with ItemProxy