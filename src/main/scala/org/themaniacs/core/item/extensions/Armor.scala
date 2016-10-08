package org.themaniacs.core.item.extensions

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor

trait Armor {
  val material: ItemArmor.ArmorMaterial
  val renderIndex: Int
  val equipmentSlot: EntityEquipmentSlot
}
