package com.example.duty_timer.screens.timer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.appSettings.entities.Timer
import com.example.duty_timer.databinding.FragmentTimerBinding
import com.example.duty_timer.screens.MainActivity
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.findTopNavController
import com.example.duty_timer.utils.viewModelCreator
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
import java.util.Date

class TimerFragment : Fragment() {
    private lateinit var binding: FragmentTimerBinding
    private val viewModel by viewModelCreator { TimerViewModel() }

    private lateinit var timer: Timer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.clearFixedRateTimer()

        binding = FragmentTimerBinding.inflate(inflater, container, false)

        binding.launchAddFriendScreen.setOnClickListener { findNavController().navigate(R.id.action_timerFragment_to_addFriendFragment) }

        binding.launchSettingsScreen.setOnClickListener { findTopNavController().navigate(R.id.action_tabsFragment_to_settingsFragment) }

        binding.launchAccountScreen.setOnClickListener { findTopNavController().navigate(R.id.action_tabsFragment_to_accountFragment) }

        binding.launchEditTimerScreen.setOnClickListener { findTopNavController().navigate(R.id.action_tabsFragment_to_editTimerFragment) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTimer()

        viewModel.percentage.observe(viewLifecycleOwner) {
            binding.timerValue.text = "$it %"

            val layoutParams = binding.timerProgressForeground.layoutParams as ConstraintLayout.LayoutParams
            val newWidth = binding.timerProgressBarbackground.width * ((it.toFloat() / 100) + 0.01)
            layoutParams.width = newWidth.toInt()
            binding.timerProgressForeground.layoutParams = layoutParams
        }

        viewModel.timerResult.observe(viewLifecycleOwner) {
            if (it is SuccessTask) {
                timer = it.value
                val currentTimeMillis = System.currentTimeMillis()

                val millisSpent = currentTimeMillis - timer.startTime
                val millisLeft = timer.endTime - currentTimeMillis

                val daysSpent = (millisSpent.toFloat() / 86400000).toInt()
                val daysLeft = (millisLeft.toFloat() / 86400000).toInt()

                binding.timeSpentValue.text = "$daysSpent дней"
                binding.timeLeftValue.text = "$daysLeft дней"
            }
        }
    }
}