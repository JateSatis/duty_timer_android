package com.example.duty_timer.screens.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentSignUpBinding
import com.example.duty_timer.utils.FragmentWithProgressBar
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.viewModelCreator

class SignUpFragment : FragmentWithProgressBar() {

    private lateinit var binding: FragmentSignUpBinding

    private val viewModel by viewModelCreator { SignUpViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        viewModel.signUpResult.observe(viewLifecycleOwner) {
            updateProgressBar(it, binding.signUpProgressBar)
            if (it is SuccessTask) findNavController().navigate(R.id.action_signUpFragment_to_tabsFragment)
        }

        binding.launchSignInScreen.setOnClickListener { findNavController().navigate(R.id.action_signUpFragment_to_signInFragment) }

        binding.launchCreateAccountScreen.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val name = binding.nameInput.text.toString()
            val nickname = binding.nicknameInput.text.toString()

            viewModel.signUp(email, password, name, nickname)
        }

        return binding.root
    }
}