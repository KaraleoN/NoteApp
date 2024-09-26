package com.geeks.noteapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.airbnb.lottie.LottieAnimationView
import com.geeks.noteapp.R
import com.geeks.noteapp.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: PreferenceHelper
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(com.google.android.material.R.drawable.abc_ic_star_black_16dp)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.playAnimation()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Установка слушателя на элементы навигации
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_onboard -> {
                    navController.navigate(R.id.onBoardFragment)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_note -> {
                    navController.navigate(R.id.noteFragment)
                    drawerLayout.closeDrawers()
                    true
                }
                R.id.nav_chat -> {
                    navController.navigate(R.id.chatFragment)
                    drawerLayout.closeDrawers()
                    true
                }
                else -> false
            }
        }

        sharedPreferences = PreferenceHelper(this@MainActivity)

        if (!sharedPreferences.isOnBoardShown) {
            sharedPreferences.isOnBoardShown = true
        } else {
            navController.navigate(R.id.noteFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        } else {
            super.onSupportNavigateUp()
        }
    }
}
