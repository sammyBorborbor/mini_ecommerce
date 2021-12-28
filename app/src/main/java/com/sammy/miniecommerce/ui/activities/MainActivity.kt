package com.sammy.miniecommerce.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.databinding.ActivityMainBinding
import com.sammy.miniecommerce.ui.fragments.home.HomeFragmentDirections
import com.sammy.miniecommerce.ui.fragments.productdetail.ProductDetailFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.categoryFragment,
            R.id.ordersFragment
        ))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun hideBottomNavigationView() {
        binding.bottomNavigationView.clearAnimation()
        binding.bottomNavigationView.animate().translationY(binding.bottomNavigationView.height.toFloat()).duration = 300
        binding.bottomNavigationView.visibility = GONE
    }

    fun showBottomNavigationView() {
        binding.bottomNavigationView.clearAnimation()
        binding.bottomNavigationView.animate().translationY(0f).duration = 300
        binding.bottomNavigationView.visibility = VISIBLE
    }

}