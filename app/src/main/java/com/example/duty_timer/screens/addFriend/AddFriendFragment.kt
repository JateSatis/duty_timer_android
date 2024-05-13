package com.example.duty_timer.screens.addFriend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentAddFriendBinding

class AddFriendFragment : Fragment() {
    private lateinit var binding: FragmentAddFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddFriendBinding.inflate(inflater, container, false)

        binding.goBackToTimerScreen.setOnClickListener { findNavController().navigate(R.id.timerFragment) }

        return binding.root
    }
}