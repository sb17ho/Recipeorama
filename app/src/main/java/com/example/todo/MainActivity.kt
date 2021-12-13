package com.example.todo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
            .navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        mainBinding.apply {
//            bottomNavBar.setupWithNavController(navController)

            NavigationUI.setupWithNavController(bottomNavBar, navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->

                if (destination.id == R.id.loginFragment2) {
                    bottomNavBar.visibility = View.GONE
                    supportActionBar!!.hide()
                } else {
                    bottomNavBar.visibility = View.VISIBLE
                    supportActionBar!!.show()
                }
            }
        }
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }
}