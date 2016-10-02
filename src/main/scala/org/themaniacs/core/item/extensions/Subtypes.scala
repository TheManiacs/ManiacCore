package org.themaniacs.core.item.extensions

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{ItemStack, Item}
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

trait Subtypes {
  @SideOnly(Side.CLIENT)
  def getSubItems(item: Item, tab: CreativeTabs): List[ItemStack]
}