package com.example.duty_timer.screens.editTimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentEditTimerBinding
import com.example.duty_timer.screens.datePicker.DateForPicker
import com.example.duty_timer.screens.datePicker.DatePickerDialogFragment
import com.example.duty_timer.screens.datePicker.DateSetListener
import com.example.duty_timer.utils.FragmentWithProgressBar
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.viewModelCreator
import java.util.Calendar
import java.util.Date
import kotlin.properties.Delegates

class EditTimerFragment : FragmentWithProgressBar() {

    private lateinit var binding: FragmentEditTimerBinding

    private val viewModel by viewModelCreator { EditTimerViewModel() }

    private lateinit var startDate: DateForPicker
    private lateinit var endDate: DateForPicker

    private var startTime by Delegates.notNull<Long>()
    private var endTime by Delegates.notNull<Long>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditTimerBinding.inflate(inflater, container, false)

        viewModel.getCurrentTimer()

        viewModel.timeResult.observe(viewLifecycleOwner) {
            updateProgressBar(it, binding.editTimerProgressBar)
            if (it is SuccessTask) {
                val timer = it.value
                val calendarStart = Calendar.getInstance()
                val calendarEnd = Calendar.getInstance()

                calendarStart.timeInMillis = timer.startTime
                startTime = timer.startTime
                calendarEnd.timeInMillis = timer.endTime
                endTime = timer.endTime

                startDate = DateForPicker(
                    calendarStart.get(Calendar.YEAR),
                    calendarStart.get(Calendar.MONTH),
                    calendarStart.get(Calendar.DAY_OF_MONTH)
                )

                endDate = DateForPicker(
                    calendarEnd.get(Calendar.YEAR),
                    calendarEnd.get(Calendar.MONTH),
                    calendarEnd.get(Calendar.DAY_OF_MONTH)
                )

                binding.editTimerStartTime.text = getFormattedDate(startDate)
                binding.editTimerEndTime.text = getFormattedDate(endDate)
            }
        }


        binding.editTimerStartTime.setOnClickListener {
            initDatePicker(it, true)
        }

        binding.editTimerEndTime.setOnClickListener {
            initDatePicker(it, false)
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigateUp()
        }

        binding.editTimerGoBack.setOnClickListener { findNavController().navigateUp() }

        binding.editTimerSaveButton.setOnClickListener {
            viewModel.updateTimer(startTime, endTime)
            findNavController().navigateUp()
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
                    startTime = time.time
                } else {
                    endDate = date
                    endTime = time.time
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