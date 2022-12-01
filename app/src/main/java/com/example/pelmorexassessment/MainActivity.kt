package com.example.pelmorexassessment

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.pelmorexassessment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.vBaseNavigationHost)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_weather, R.id.navigation_gallery, R.id.navigation_contactus
            )
        )
    }

    override fun onStart() {
        super.onStart()
        findNavController(R.id.vBaseNavigationHost)?.let {
            binding.bottomNavigation.setupWithNavController(
                it
            )
            it.addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.navigation_weather, R.id.navigation_gallery, R.id.navigation_contactus -> showBottomNav()
                    else -> hideBottomNav()
                }
            }
        }
    }

    fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }
}