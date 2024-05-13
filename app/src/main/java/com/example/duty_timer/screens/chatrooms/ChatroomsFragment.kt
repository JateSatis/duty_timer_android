package com.example.duty_timer.screens.chatrooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.duty_timer.databinding.FragmentChatroomsBinding

class ChatroomsFragment : Fragment() {
    private lateinit var binding: FragmentChatroomsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatroomsBinding.inflate(inflater, container, false)

        return binding.root
    }
}