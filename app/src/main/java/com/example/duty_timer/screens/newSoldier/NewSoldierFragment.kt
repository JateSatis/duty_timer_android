package com.example.duty_timer.screens.newSoldier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentNewSoldierBinding

class NewSoldierFragment : Fragment() {
    private lateinit var binding: FragmentNewSoldierBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewSoldierBinding.inflate(inflater, container, false)

        binding.goBackToRoleScreen.setOnClickListener { findNavController().navigateUp() }

        binding.launchSignUpScreen.setOnClickListener { findNavController().navigate(R.id.action_newSoldierFragment_to_signUpFragment) }

        return binding.root
    }
}