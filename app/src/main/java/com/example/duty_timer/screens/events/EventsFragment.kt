package com.example.duty_timer.screens.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.duty_timer.databinding.FragmentEventsBinding
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.ViewModelCreator
import com.example.duty_timer.utils.viewModelCreator

class EventsFragment : Fragment() {

    private lateinit var binding: FragmentEventsBinding

    private val viewModel by viewModelCreator { EventsViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)

        viewModel.getAllEvents()

        val adapter = EventsAdapter(object : EventItemListener {
                override fun onLongClick(id: Int) {
                    val deleteEventDialog = DeleteEventDialogFragment(id, viewModel);
                    deleteEventDialog.show(parentFragmentManager, null)
                }
            }
        )
        val layoutManager = LinearLayoutManager(requireContext())

        binding.eventsRecyclerContainer.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
        }

        binding.eventsAddEventButton.setOnClickListener {
            val addEventDialog = AddEventDialogFragment()
            addEventDialog.setOnAddEventListener(onAddEventListener)
            addEventDialog.show(parentFragmentManager, null)
        }

        viewModel.eventsResult.observe(viewLifecycleOwner) {
            if (it is SuccessTask && it.value != null) {
                Log.d("EVENTS!", "${it.value.size}")
                adapter.events = adapter.map(it.value)
                adapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }

    private val onAddEventListener = object : OnAddEventListener {
        override fun onAddEvent(title: String, millis: Long) {
            viewModel.addEvent(title, millis)
        }
    }
}