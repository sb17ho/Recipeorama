package com.example.todo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.todo.databinding.ActivityMainBinding

/* @author Simar Bhamra (6364665)
 * @author Fahad Ansar
 * */

/*This is the main activity which only apply functionality for
* Navigation Components (NavHostFragment) and Bottom Navigation*/
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

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}