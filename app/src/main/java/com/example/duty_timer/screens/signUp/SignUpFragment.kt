package com.example.duty_timer.screens.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.launchSignInScreen.setOnClickListener { findNavController().navigate(R.id.action_signUpFragment_to_signInFragment) }

        binding.launchCreateAccountScreen.setOnClickListener { findNavController().navigate(R.id.action_signUpFragment_to_createAccountFragment) }

        return binding.root
    }
}