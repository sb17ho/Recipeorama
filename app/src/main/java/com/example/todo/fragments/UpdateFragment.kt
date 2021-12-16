package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.adapter.IngredientsAdapter
import com.example.todo.data.Task
import com.example.todo.databinding.FragmentUpdateBinding
import com.example.todo.viewModel.TodoViewModel

class UpdateFragment : Fragment() {
    private lateinit var updateFragment: FragmentUpdateBinding
    private val viewModel: TodoViewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }
    private val ingredientsRecyclerForRequired by lazy {
        IngredientsAdapter()
    }
    private val ingredientsRecyclerForToBuy by lazy {
        IngredientsAdapter()
    }
    private var requiredIngredientsList: MutableList<String> = mutableListOf()
    private var toBuyIngredientsList: MutableList<String> = mutableListOf()

    private var liveRequiredIngredientsList: MutableLiveData<List<String>> = MutableLiveData()
    private var liveToBuyIngredientsList: MutableLiveData<List<String>> = MutableLiveData()

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        updateFragment = FragmentUpdateBinding.inflate(inflater, container, false)

        //Required Adapter
        if (args.updateCurrentItem.requiredIngredients.isNotEmpty()) {
            args.updateCurrentItem.requiredIngredients.split(",").forEach {
                requiredIngredientsList.add(it)
            }
        }

        requiredIngredientsList = requiredIngredientsList.filter {
            it.isNotEmpty()
        }.toMutableList()

        liveRequiredIngredientsList.value = requiredIngredientsList
        liveRequiredIngredientsList.observe(viewLifecycleOwner) {
            ingredientsRecyclerForRequired.setList(it)
        }

        ingredientsRecyclerForRequired.setOnItemClickListener(object :
            IngredientsAdapter.OnItemClickListener {
            override fun onItemClickListener(ingredient: String) {
                requiredIngredientsList.remove(ingredient)
                if (!toBuyIngredientsList.contains(ingredient)) {
                    toBuyIngredientsList.add(ingredient)
                }

                liveRequiredIngredientsList.value = requiredIngredientsList
                liveToBuyIngredientsList.value = toBuyIngredientsList
            }
        })

        //To Buy Adapter
        if (args.updateCurrentItem.toBuyIngredients.isNotEmpty()) {
            args.updateCurrentItem.toBuyIngredients.split(",").forEach {
                toBuyIngredientsList.add(it)
            }
        }

        toBuyIngredientsList = toBuyIngredientsList.filter {
            it.isNotEmpty()
        }.toMutableList()

        liveToBuyIngredientsList.value = toBuyIngredientsList
        liveToBuyIngredientsList.observe(viewLifecycleOwner) {
            ingredientsRecyclerForToBuy.setList(it)
        }

        ingredientsRecyclerForToBuy.setOnItemClickListener(object :
            IngredientsAdapter.OnItemClickListener {
            override fun onItemClickListener(ingredient: String) {
                toBuyIngredientsList.remove(ingredient)
                if (!requiredIngredientsList.contains(ingredient)) {
                    requiredIngredientsList.add(ingredient)
                }

                liveRequiredIngredientsList.value = requiredIngredientsList
                liveToBuyIngredientsList.value = toBuyIngredientsList
            }
        })


        updateFragment.apply {

            requiredItemsId.layoutManager = LinearLayoutManager(requireContext())
            requiredItemsId.adapter = ingredientsRecyclerForRequired

            tobuyItemsId.layoutManager = LinearLayoutManager(requireContext())
            tobuyItemsId.adapter = ingredientsRecyclerForToBuy

            doneButton.setOnClickListener {
                updateDatabaseItem()
                findNavController().popBackStack(R.id.listItems, false)
            }
        }
        return updateFragment.root
    }

    fun updateDatabaseItem() {
        val title = args.updateCurrentItem.title
        var toBuyString = ""
        var requiredString = ""

        toBuyIngredientsList.forEach {
            toBuyString += "$it,"
        }

        requiredIngredientsList.forEach {
            requiredString += "$it,"
        }

        if (!viewModel.checkIfNotEmpty(title)) {

            val task: Task = args.updateCurrentItem
            task.title = title
            task.toBuyIngredients = toBuyString
            task.requiredIngredients = requiredString
            viewModel.updateTask(task)
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        } else {
            updateFragment.apply {
            }
        }
    }

    //To override the action bar title
    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
            .setActionBarTitle(args.updateCurrentItem.title)
    }
}