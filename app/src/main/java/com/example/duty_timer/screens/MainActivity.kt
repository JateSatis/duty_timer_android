package com.example.duty_timer.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.duty_timer.R
import com.example.duty_timer.databinding.ActivityMainBinding
import com.example.duty_timer.appSettings.SharedPreferencesAppSettings

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedPreferencesSavedAuth: SharedPreferencesAppSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        val intent = Intent()

        val navHost = supportFragmentManager.findFragmentById(R.id.mainGraphContainer) as NavHostFragment
        val navController = navHost.navController

        sharedPreferencesSavedAuth = SharedPreferencesAppSettings(applicationContext)

        if (isSignedIn()) {
            navController.navigate(R.id.tabsFragment)
        }

    }

    fun isSignedIn(): Boolean {
        return sharedPreferencesSavedAuth.getCurrentToken() != null
    }
}