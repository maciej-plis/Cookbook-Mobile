package com.example.cookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        handleInputArgs()

        binding.recipesFragmentRvRecipes.apply {
            adapter = RecipesAdapter(RecipesDatabase.recipes)
            layoutManager = LinearLayoutManager(activity)
        }

        binding.recipesFragmentSwipe.let { it.setOnRefreshListener { it.isRefreshing = false } }

        binding.recipesFragmentFabSaveRecipe.setOnClickListener {
            findNavController().navigate(R.id.navGraph_recipesFragment_action_createRecipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleInputArgs() = arguments?.run {
        getParcelable<Recipe>("recipe")?.let { getRecipesAdapter().addRecipe(it) }
        clear()
    }


    private fun getRecipesAdapter(): RecipesAdapter = binding.recipesFragmentRvRecipes.adapter as RecipesAdapter
}