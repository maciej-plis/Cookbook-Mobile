package com.example.cookbook.recipes_activity.recipes_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.R
import com.example.cookbook.databinding.FragmentRecipesBinding
import com.example.cookbook.recipes_activity.Recipe

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

        setHasOptionsMenu(true)
        handleInputArgs()

        binding.recipesFragmentRvRecipes.apply {
            adapter = RecipesAdapter(RecipesDatabase.recipes)
            layoutManager = LinearLayoutManager(activity)
        }

        binding.recipesFragmentSwipe.let { it.setOnRefreshListener { it.isRefreshing = false } }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_recipes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_recipes_add_recipe -> {
                findNavController().navigate(R.id.navGraph_recipesFragment_action_createRecipe)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleInputArgs() = arguments?.run {
        getParcelable<Recipe>("recipe")?.let { getRecipesAdapter().addRecipe(it) }
        clear()
    }


    private fun getRecipesAdapter(): RecipesAdapter = binding.recipesFragmentRvRecipes.adapter as RecipesAdapter
}