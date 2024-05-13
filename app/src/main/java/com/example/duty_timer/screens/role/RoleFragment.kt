package com.example.duty_timer.screens.role

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentRoleBinding

class RoleFragment : Fragment() {
    private lateinit var binding: FragmentRoleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoleBinding.inflate(inflater, container, false)

        binding.skipAccountSetup.setOnClickListener { findNavController().navigate(R.id.action_roleFragment_to_tabsFragment) }
        binding.launchNewSoldierScreen.setOnClickListener { findNavController().navigate(R.id.action_roleFragment_to_newSoldierFragment) }

        return binding.root
    }
}