package org.themaniacs.core.recipe

import java.io.File

import de.kilobyte22.config.{ConfigElement, ConfigFile}
import org.themaniacs.core.ManiacCore
import org.themaniacs.core.util.{ConfigurationException, FileExtensions, file}

import scala.collection.convert.wrapAsScala._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object RecipeLoader {
  private val recipeHandlers = mutable.Map.empty[String, RecipeHandler]

  def getRecipeHandler(ty: String) = recipeHandlers.get(ty)
}

class RecipeLoader {
  private val recipes = mutable.Map.empty[String, Recipe]

  def loadRecipesForMod(name: String) = {
    val recipes = loadRecipesFromFile(name, fileForRecipes(name, "default"))
    recipes.foreach(r => {
      r.handler.registerRecipe(r.id, r.block)
    })
  }

  private def fileForRecipes(mod: String, config: String) = {
    file("config") / ManiacCore.ModID / "recipes" / mod / (config + ".cfg")
  }

  private def loadRecipesFromFile(mod: String, file: File): List[Recipe] = {
    val data = new ConfigFile(file)
    loadRecipes(mod, data)
  }

  private def loadRecipes(mod: String, config: ConfigElement): List[Recipe] = {
    val ret = ArrayBuffer.empty[Recipe]
    config.getOptions.foreach(opt => {
      opt.getName match {
        case "import" =>
          ret ++= loadRecipesFromFile(mod, fileForRecipes(mod, Option(opt.getString(0)).getOrElse(throw new ConfigurationException("Import statement needs a parameter in recipe config"))))
        case "recipe" =>
          val ty = Option(opt.getString(0)).getOrElse(throw new ConfigurationException("recipe option is missing parameter type"))
          val id = Option(opt.getString(0)).getOrElse(throw new ConfigurationException("recipe option is missing parameter id"))

          ty match {
            case "delete" =>
              recipes.remove(id)
            case _ =>
              val h = RecipeLoader.getRecipeHandler(ty).getOrElse(throw new ConfigurationException(s"Unknown recipe handler $ty"))
              recipes(id) = Recipe(id, ty, Option(opt.getSub).getOrElse(throw new ConfigurationException("Must specify a block for the recipe statement")), h)
          }
        case n => throw new ConfigurationException(s"Invalid config option $n in recipe config")
      }
    })
    ret.toList
  }
  case class Recipe(id: String, ty: String, block: ConfigElement, handler: RecipeHandler)
}
