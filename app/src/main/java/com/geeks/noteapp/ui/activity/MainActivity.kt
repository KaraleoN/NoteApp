package com.geeks.noteapp.ui.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieAnimationView
import com.geeks.noteapp.R
import com.geeks.noteapp.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: PreferenceHelper
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        lottieAnimationView.playAnimation()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        sharedPreferences = PreferenceHelper(this@MainActivity)
        sharedPreferences.text

        if (!sharedPreferences.isOnBoardShown) {
            sharedPreferences.isOnBoardShown = true
        } else {
            navController.navigate(R.id.noteFragment)
        }
    }
}