package org.themaniacs.core.recipe

import de.kilobyte22.config.ConfigElement

trait RecipeHandler {
  def registerRecipe(id: String, config: ConfigElement)
}
