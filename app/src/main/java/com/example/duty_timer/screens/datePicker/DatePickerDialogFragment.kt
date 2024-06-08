package com.example.duty_timer.screens.datePicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.duty_timer.databinding.DialogDatepickerBinding
import java.util.Calendar
import java.util.Date

interface DateSetListener {
    fun onDateSet(date: DateForPicker, time: Date)
}

class DatePickerDialogFragment : DialogFragment() {

    private lateinit var binding: DialogDatepickerBinding
    private lateinit var dateSetListener: DateSetListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDatepickerBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()

        val defaultDate = DateForPicker(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH),
            day = calendar.get(Calendar.DAY_OF_MONTH)
        )

        val date = arguments?.getSerializable(ARG_DATE) as DateForPicker? ?: defaultDate

        binding.datePicker.updateDate(date.year, date.month, date.day)

        binding.datePickerSaveButton.setOnClickListener {
            date.apply {
                this.year = binding.datePicker.year
                this.month = binding.datePicker.month
                this.day = binding.datePicker.dayOfMonth
            }

            calendar.set(date.year, date.month, date.day)

            dateSetListener.onDateSet(date, calendar.time)
            dismiss()
        }

        return binding.root
    }

    fun setDateSetListener(dateSetListener: DateSetListener) {
        this.dateSetListener = dateSetListener
    }

    companion object {
        fun newInstance(date: DateForPicker): DatePickerDialogFragment {
            val args = bundleOf(ARG_DATE to date)
            val fragment = DatePickerDialogFragment()
            fragment.arguments = args
            return fragment
        }

        private const val ARG_DATE = "ARG_DATE"
    }
}

//На всякий случай doubleDatePicker
//package com.example.duty_timer.screens.datePicker
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.os.bundleOf
//import androidx.fragment.app.DialogFragment
//import com.example.duty_timer.databinding.DialogDatepickerBinding
//import java.util.Calendar
//import java.util.Date
//
//interface DoubleDateSetListener {
//    fun onDateSet(startDate: DateForPicker, endDate: DateForPicker, startTime: Date, endTime: Date)
//}
//
//class DoubleDatePickerDialogFragment : DialogFragment() {
//
//    private lateinit var binding: DialogDatepickerBinding
//    private lateinit var dateSetListener: DateSetListener
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = DialogDatepickerBinding.inflate(inflater, container, false)
//
//        val calendar = Calendar.getInstance()
//
//        val defaultStartDate = DateForPicker(
//            year = calendar.get(Calendar.YEAR),
//            month = calendar.get(Calendar.MONTH),
//            day = calendar.get(Calendar.DAY_OF_MONTH)
//        )
//
//        val defaultEndDate = DateForPicker(
//            year = calendar.get(Calendar.YEAR) + 1,
//            month = calendar.get(Calendar.MONTH),
//            day = calendar.get(Calendar.DAY_OF_MONTH)
//        )
//
//        val startDate = arguments?.getSerializable(ARG_START_DATE) as DateForPicker? ?: defaultStartDate
//        val endDate = arguments?.getSerializable(ARG_END_DATE) as DateForPicker? ?: defaultEndDate
//
//        binding.startDatePicker.updateDate(startDate.year, startDate.month, startDate.day)
//        binding.endDatePicker.updateDate(endDate.year, endDate.month, endDate.day)
//
//        binding.datePickerSaveButton.setOnClickListener {
//            startDate.apply {
//                this.year = binding.startDatePicker.year
//                this.month = binding.startDatePicker.month
//                this.day = binding.startDatePicker.dayOfMonth
//            }
//
//            endDate.apply {
//                this.year = binding.endDatePicker.year
//                this.month = binding.endDatePicker.month
//                this.day = binding.endDatePicker.dayOfMonth
//            }
//
//            val startCalendar = Calendar.getInstance()
//            startCalendar.set(startDate.year, startDate.month, startDate.day)
//            val endCalendar = Calendar.getInstance()
//            endCalendar.set(endDate.year, endDate.month, endDate.day)
//
//            dateSetListener.onDateSet(startDate, endDate, startCalendar.time, endCalendar.time)
//            dismiss()
//        }
//
//        return binding.root
//    }
//
//    fun setDateSetListener(dateSetListener: DateSetListener) {
//        this.dateSetListener = dateSetListener
//    }
//
//    companion object {
//        fun newInstance(startDate: DateForPicker, endDate: DateForPicker): DatePickerDialogFragment {
//            val args = bundleOf(ARG_START_DATE to startDate, ARG_END_DATE to endDate)
//            val fragment = DatePickerDialogFragment()
//            fragment.arguments = args
//            return fragment
//        }
//
//        private const val ARG_START_DATE = "ARG_START_DATE"
//        private const val ARG_END_DATE = "ARG_END_DATE"
//    }
//}