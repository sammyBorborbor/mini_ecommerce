package com.sammy.miniecommerce.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.sammy.miniecommerce.R
import com.sammy.miniecommerce.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        navController = Navigation.findNavController(this, R.id.navHostFragment)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}