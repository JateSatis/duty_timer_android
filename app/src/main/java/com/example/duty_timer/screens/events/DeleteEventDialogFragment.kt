package com.example.duty_timer.screens.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.duty_timer.databinding.DialogDeleteEventBinding

class DeleteEventDialogFragment(
    private val eventId: Int,
    private val viewModel: EventsViewModel
) : DialogFragment() {

    private lateinit var binding: DialogDeleteEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDeleteEventBinding.inflate(inflater, container, false)

        binding.deleteItemCancel.setOnClickListener {
            dismiss()
        }

        binding.deleteItemAccept.setOnClickListener {
            viewModel.deleteEvent(eventId)
            dismiss()
        }

        return binding.root
    }

}