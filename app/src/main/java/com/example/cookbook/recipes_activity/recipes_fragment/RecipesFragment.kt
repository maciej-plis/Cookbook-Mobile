package com.example.cookbook.recipes_activity.recipes_fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.R
import com.example.cookbook.RetrofitEventListener
import com.example.cookbook.databinding.FragmentRecipesBinding
import com.example.cookbook.recipes_activity.Recipe
import com.example.cookbook.recipes_activity.RecipesApi
import com.example.cookbook.recipes_activity.RecipesApiRestClient
import retrofit2.Call

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

        binding.recipesFragmentRvRecipes.apply {
            adapter = RecipesAdapter()
            layoutManager = LinearLayoutManager(activity)
        }

        setHasOptionsMenu(true)

        binding.recipesFragmentSwipe.setOnRefreshListener {
            RecipesApiRestClient.getRecipes(GetRecipesEventListener())
        }

        RecipesApiRestClient.getRecipes(GetRecipesEventListener())
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

    private fun getRecipesAdapter(): RecipesAdapter = binding.recipesFragmentRvRecipes.adapter as RecipesAdapter

    inner class GetRecipesEventListener : RetrofitEventListener<List<Recipe>> {
        override fun onSuccess(call: Call<List<Recipe>>, response: List<Recipe>) {
            getRecipesAdapter().setRecipes(response)
            binding.recipesFragmentSwipe.isRefreshing = false
        }

        override fun onError(call: Call<List<Recipe>>, t: Throwable) {
            Toast.makeText(activity, "Unable to download recipes", LENGTH_SHORT).show()
            binding.recipesFragmentSwipe.isRefreshing = false
        }
    }
}