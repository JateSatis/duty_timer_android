package com.example.duty_timer.screens.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentSignInBinding
import com.example.duty_timer.utils.FragmentWithProgressBar
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.viewModelCreator

class SignInFragment : FragmentWithProgressBar() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModelCreator { SignInViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.goBackToSignUpScreen.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signInResult.observe(viewLifecycleOwner) {
            updateProgressBar(it, binding.signInProgressBar)
            if (it is SuccessTask) findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
        }

        binding.signIn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.signIn(email, password)
        }
    }
}