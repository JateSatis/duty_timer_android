package com.example.duty_timer.screens.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentTimerBinding
import com.example.duty_timer.screens.MainActivity
import com.example.duty_timer.utils.findTopNavController
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater, container, false)

        binding.launchAddFriendScreen.setOnClickListener { findNavController().navigate(R.id.action_timerFragment_to_addFriendFragment) }

        binding.launchSettingsScreen.setOnClickListener { findTopNavController().navigate(R.id.action_tabsFragment_to_settingsFragment) }


        return binding.root
    }
}