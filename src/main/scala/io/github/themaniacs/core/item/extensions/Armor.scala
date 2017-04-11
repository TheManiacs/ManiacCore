package io.github.themaniacs.core.item.extensions

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor

trait Armor {
  // prevent implementation of multiple exclusive traits
  final def needsSpecialProxy(): Unit = {}
  final def needsExclusiveSubtypes(): Unit = {}

  val material: ItemArmor.ArmorMaterial
  val renderIndex: Int
  val equipmentSlot: EntityEquipmentSlot
}
