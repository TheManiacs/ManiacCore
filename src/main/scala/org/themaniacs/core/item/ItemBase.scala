package org.themaniacs.core.item

import net.minecraft.creativetab.CreativeTabs

abstract class ItemBase {
  val id: String
  val maxStackSize: Int
  val creativeTab: Option[CreativeTabs]
}