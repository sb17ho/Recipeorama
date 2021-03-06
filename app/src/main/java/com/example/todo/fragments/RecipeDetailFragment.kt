package com.example.todo.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todo.MainActivity
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentRecipeDetailBinding
import com.example.todo.viewModel.TodoViewModel
import com.squareup.picasso.Picasso
import java.util.*

/*
* The purpose of this class is to provide the user with the details,
* of the recipe clicked by the user. It handles the dynamic link URL,
*  for user to access either the article for making the recipe or a youtube video
* */
class RecipeDetailFragment : Fragment() {
    private lateinit var recipeDetailFragment: FragmentRecipeDetailBinding
    private val recipeInfoArgs by navArgs<RecipeDetailFragmentArgs>()
    private var ingredientsList: MutableList<String> = mutableListOf()
    private var instructions: MutableList<String> = mutableListOf()
    private var requiredIngredientsString: String = ""
    private val viewModel by lazy {
        ViewModelProvider(this)[TodoViewModel::class.java]
    }

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

        ingredientsList = ingredientsList.filter { item: String? ->
            item != null && item.isNotEmpty()
        }.toMutableList()

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

        instructions = instructions.filter { item: String? ->
            item != null && item.isNotEmpty()
        }.toMutableList()

        ingredientsList.forEach {
            requiredIngredientsString += "$it,"
        }

        recipeDetailFragment.apply {
            recipeTitleViewId.text = recipeInfoArgs.recipeInfo.strMeal
            Picasso.with(imageViewId.context).load(recipeInfoArgs.recipeInfo.strMealThumb)
                .into(imageViewId)
            categoryId.text = recipeInfoArgs.recipeInfo.strCategory
            areaId.text = recipeInfoArgs.recipeInfo.strArea

            //To create dynamic link for the user to click and open resources
            var dynamicUrl = recipeInfoArgs.recipeInfo.strSource

            var linkedText =
                String.format(
                    "<a href=\"%s\">${recipeInfoArgs.recipeInfo.strSource}</a>",
                    dynamicUrl
                )

            sourceId.text = Html.fromHtml(linkedText)
            sourceId.movementMethod = LinkMovementMethod.getInstance()

            dynamicUrl = recipeInfoArgs.recipeInfo.strYoutube
            linkedText = String.format(
                "<a href=\"%s\">${recipeInfoArgs.recipeInfo.strYoutube}</a>",
                dynamicUrl
            )

            youtubeId.text = Html.fromHtml(linkedText)
            youtubeId.movementMethod = LinkMovementMethod.getInstance()

            var ingredientsStr: String = ""
            ingredientsList.forEach {
                ingredientsStr += "$it, "
            }

            ingredientsId.text = ingredientsStr

            var measurement: String = ""

            if (instructions.size >= ingredientsList.size) {
                for (i in 0 until ingredientsList.size) {
                    measurement += "${instructions[i]} ${ingredientsList[i]} , "
                }
            } else if (instructions.size < ingredientsList.size) {
                for (i in 0 until instructions.size) {
                    measurement += "${instructions[i]} ${ingredientsList[i]} , "
                }
            }

            measurementsId.text = measurement

            addTitleToRoomId.setOnClickListener {
                insertMealTitleToDatabase()
            }
        }

        return recipeDetailFragment.root
    }

    /*
    * Insert the meal information to the database
    * */
    private fun insertMealTitleToDatabase() {

        val calendar = Calendar.getInstance()
        val task = Task(
            title = recipeInfoArgs.recipeInfo.strMeal,
            requiredIngredients = requiredIngredientsString,
            toBuyIngredients = "",
            dd = calendar[Calendar.DATE],
            mm = calendar[Calendar.MONTH],
            yy = calendar[Calendar.YEAR],
            userEmail = viewModel.getCurrentUserEmail().toString()
        )

        viewModel.addTask(task)
        findNavController().popBackStack()
        Toast.makeText(
            requireContext(),
            "Title Added, Go To Home To Add Purchase Ingredients",
            Toast.LENGTH_SHORT
        ).show()

    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle(recipeInfoArgs.recipeInfo.strMeal)
    }
}