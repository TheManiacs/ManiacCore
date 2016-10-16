package org.themaniacs.core.item.extensions

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor

trait Armor {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy() = {}
  final def needsExclusiveSubtypes() = {}

  val material: ItemArmor.ArmorMaterial
  val renderIndex: Int
  val equipmentSlot: EntityEquipmentSlot
}
