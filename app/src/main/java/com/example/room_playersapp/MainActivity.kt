package com.example.room_playersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.room_playersapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        // Action Bar
        setupActionBarWithNavController(this, navController)


//        replaceFragment(PlayerListFragment())
    }

//    private fun replaceFragment(fragment: Fragment) {
//
//        supportFragmentManager.beginTransaction().apply {
//
//            replace(R.id.navHostFragment, fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}