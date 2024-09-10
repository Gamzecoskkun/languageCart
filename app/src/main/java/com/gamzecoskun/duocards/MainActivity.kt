package com.gamzecoskun.duocards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gamzecoskun.duocards.common.viewBinding
import com.gamzecoskun.duocards.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var navController: NavController

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val navHostController = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostController.navController
        binding.bottomNav.setupWithNavController(navController)

        bottomNav=binding.bottomNav


        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.wordListFragment -> {
                    navController.navigate(R.id.wordListFragment)
                    true
                }
                R.id.learnedWordsFragment -> {
                    navController.navigate(R.id.learnedWordsFragment)
                    true
                }
                else -> false
            }
        }
    }
}