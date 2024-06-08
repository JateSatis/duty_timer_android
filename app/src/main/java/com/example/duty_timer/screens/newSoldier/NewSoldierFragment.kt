package com.example.duty_timer.screens.newSoldier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentNewSoldierBinding
import com.example.duty_timer.screens.datePicker.DateForPicker
import com.example.duty_timer.screens.datePicker.DatePickerDialogFragment
import com.example.duty_timer.screens.datePicker.DateSetListener
import com.example.duty_timer.utils.viewModelCreator
import java.util.Calendar
import java.util.Date

class NewSoldierFragment : Fragment() {

    private lateinit var binding: FragmentNewSoldierBinding

    private val viewModel by viewModelCreator { NewSoldierViewModel() }

    private lateinit var startDate: DateForPicker
    private lateinit var endDate: DateForPicker

    private lateinit var startTime: Date
    private lateinit var endTime: Date

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewSoldierBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        startDate = DateForPicker(year, month, day)
        endDate = DateForPicker(year + 1, month, day)

        calendar.set(year, month, day)
        startTime = calendar.time
        calendar.set(year + 1, month, day)
        endTime = calendar.time

        startDate.run {
            binding.newSoldierStartTime.text = getFormattedDate(startDate)
        }

        endDate.run {
            binding.newSoldierEndTime.text = getFormattedDate(endDate)
        }


        binding.newSoldierStartTime.setOnClickListener {
            initDatePicker(it, true)
        }

        binding.newSoldierEndTime.setOnClickListener {
            initDatePicker(it, false)
        }

        binding.launchSignUpScreen.setOnClickListener {
            viewModel.saveTimer(startTime.time, endTime.time)
            findNavController().navigate(R.id.action_newSoldierFragment_to_signUpFragment)
        }

        return binding.root
    }

    private fun initDatePicker(it: View, startPicker: Boolean) {
        val dateView = it as TextView
        val date = if (startPicker) startDate else endDate
        val datePickerDialog = DatePickerDialogFragment.newInstance(date)

        datePickerDialog.setDateSetListener(object : DateSetListener {
            override fun onDateSet(date: DateForPicker, time: Date) {
                if (startPicker) {
                    startDate = date
                    startTime = time
                } else {
                    endDate = date
                    endTime = time
                }
                dateView.text = getFormattedDate(date)

            }
        })

        datePickerDialog.show(parentFragmentManager, null)
    }

    private fun getMonthFromIndex(monthIndex: Int): String {
        val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентябра", "Октября", "Ноября", "Декабря")
        return months[monthIndex]
    }

    private fun getFormattedDate(date: DateForPicker = startDate) : String {
        return "${date.day} ${getMonthFromIndex(date.month)}, ${date.year}"
    }
}

// If ever needed: NewSoldier for double date picker
//package com.example.duty_timer.screens.newSoldier
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.findNavController
//import com.example.duty_timer.R
//import com.example.duty_timer.databinding.FragmentNewSoldierBinding
//import com.example.duty_timer.screens.datePicker.DateForPicker
//import com.example.duty_timer.screens.datePicker.DatePickerDialogFragment
//import com.example.duty_timer.screens.datePicker.DateSetListener
//import com.example.duty_timer.utils.viewModelCreator
//import java.util.Calendar
//import java.util.Date
//
//class NewSoldierFragment : Fragment() {
//
//    private lateinit var binding: FragmentNewSoldierBinding
//
//    private val viewModel by viewModelCreator { NewSoldierViewModel() }
//
//    private lateinit var startDate: DateForPicker
//    private lateinit var endDate: DateForPicker
//
//    private lateinit var startTime: Date
//    private lateinit var endTime: Date
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentNewSoldierBinding.inflate(inflater, container, false)
//
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        startDate = DateForPicker(year, month, day)
//        endDate = DateForPicker(year + 1, month, day)
//
//        calendar.set(year, month, day)
//        startTime = calendar.time
//        calendar.set(year + 1, month, day)
//        endTime = calendar.time
//
//        startDate.run {
//            binding.newSoldierStartTime.text = getFormattedDate(startDate)
//        }
//
//        endDate.run {
//            binding.newSoldierEndTime.text = getFormattedDate(endDate)
//        }
//
//
//        binding.newSoldierChangeTime.setOnClickListener {
//            initDatePicker()
//        }
//
//        binding.goBackToRoleScreen.setOnClickListener { findNavController().navigateUp() }
//
//        binding.launchSignUpScreen.setOnClickListener {
//            viewModel.saveTimer(startTime.time, endTime.time)
//            findNavController().navigate(R.id.action_newSoldierFragment_to_signUpFragment)
//        }
//
//        return binding.root
//    }
//
//    private fun initDatePicker() {
//        val datePickerDialog = DatePickerDialogFragment.newInstance(startDate, endDate)
//
//        datePickerDialog.setDateSetListener(object : DateSetListener {
//            override fun onDateSet(
//                startDate: DateForPicker,
//                endDate: DateForPicker,
//                startTime: Date,
//                endTime: Date
//            ) {
//                val fragment = this@NewSoldierFragment
//                fragment.startDate = startDate
//                fragment.endDate = endDate
//                fragment.startTime = startTime
//                fragment.endTime = endTime
//            }
//        })
//
//        datePickerDialog.show(parentFragmentManager, null)
//    }
//
//    private fun getMonthFromIndex(monthIndex: Int): String {
//        val months = arrayOf("Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентябра", "Октября", "Ноября", "Декабря")
//        return months[monthIndex]
//    }
//
//    private fun getFormattedDate(date: DateForPicker = startDate) : String {
//        return "${date.day} ${getMonthFromIndex(date.month)}, ${date.year}"
//    }
//}