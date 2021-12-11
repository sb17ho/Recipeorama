package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.todo.databinding.FragmentRecipeDetailBinding
import com.squareup.picasso.Picasso

class RecipeDetailFragment : Fragment() {
    private lateinit var recipeDetailFragment: FragmentRecipeDetailBinding
    private val recipeInfoArgs by navArgs<RecipeDetailFragmentArgs>()
    private val ingredientsList: MutableList<String> = mutableListOf()
    private val instructions: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipeDetailFragment = FragmentRecipeDetailBinding.inflate(layoutInflater, container, false)

        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient1)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient2)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient3)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient4)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient5)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient6)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient7)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient8)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient9)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient10)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient11)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient12)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient13)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient14)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient15)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient16)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient17)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient18)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient19)
        ingredientsList.add(recipeInfoArgs.recipeInfo.strIngredient20)
        while (ingredientsList.remove("")) {
        }

        instructions.add(recipeInfoArgs.recipeInfo.strMeasure1)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure2)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure3)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure4)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure5)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure6)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure7)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure8)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure9)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure10)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure11)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure12)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure13)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure14)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure15)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure16)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure17)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure18)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure19)
        instructions.add(recipeInfoArgs.recipeInfo.strMeasure20)
        while (instructions.remove("")) {
        }

        recipeDetailFragment.apply {
            recipeTitleViewId.text = recipeInfoArgs.recipeInfo.strMeal
            Picasso.with(imageViewId.context).load(recipeInfoArgs.recipeInfo.strMealThumb)
                .into(imageViewId)
            categoryId.text = recipeInfoArgs.recipeInfo.strCategory
            areaId.text = recipeInfoArgs.recipeInfo.strArea

            sourceId.text = recipeInfoArgs.recipeInfo.strSource
            youtubeId.text = recipeInfoArgs.recipeInfo.strYoutube

            var ingredientsStr: String = ""
            ingredientsList.forEach {
                ingredientsStr += "$it, "
            }

            ingredientsId.text = ingredientsStr

            var measurement: String = ""

            for (i in 0 until instructions.size) {
                measurement += "${instructions[i]} ${ingredientsList[i]} , "
            }

            measurementsId.text = measurement

        }

        return recipeDetailFragment.root
    }

}