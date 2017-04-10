package io.github.themaniacs.core.block

import net.minecraft.item.{ItemBlock, ItemStack}

class GeneralItemBlock(block: BlockProxy) extends ItemBlock(block) {
  setHasSubtypes(false)
  setMaxDamage(0)

  override def getUnlocalizedName(stack: ItemStack) = getUnlocalizedName

  override def getMetadata(damage: Int) = damage
}
