package com.example.duty_timer.screens.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.duty_timer.databinding.DialogAddEventBinding
import java.util.Calendar

interface OnAddEventListener {
    fun onAddEvent(title: String, millis: Long)
}

class AddEventDialogFragment : DialogFragment() {

    private lateinit var binding: DialogAddEventBinding
    private lateinit var onAddEventListener: OnAddEventListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddEventBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.addEventDatePicker.updateDate(year, month, day)

        binding.addEventSaveButton.setOnClickListener {
            val title = binding.addEventTitleInput.text.toString()
            calendar.set(
                binding.addEventDatePicker.year,
                binding.addEventDatePicker.month,
                binding.addEventDatePicker.dayOfMonth
            )
            val millis = calendar.time.time
            onAddEventListener.onAddEvent(title, millis)
            dismiss()
        }

        return binding.root
    }

    fun setOnAddEventListener(listener: OnAddEventListener) {
        onAddEventListener = listener
    }

}