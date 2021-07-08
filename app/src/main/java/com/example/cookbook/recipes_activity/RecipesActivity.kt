package com.example.cookbook.recipes_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cookbook.R
import com.example.cookbook.databinding.ActivityRecipesBinding

class RecipesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipesBinding
    private lateinit var navigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.recipesActivityToolbar)

        navigation = findNavController(R.id.recipesActivity_navHost)
        setupActionBarWithNavController(navigation, AppBarConfiguration(navigation.graph))
    }

    override fun onSupportNavigateUp(): Boolean = navigation.navigateUp() || super.onSupportNavigateUp()
}