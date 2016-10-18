package org.themaniacs.core.item

import net.minecraft.item.Item

class ItemProxyBase(val impl: ItemBase) extends Item with ItemProxy