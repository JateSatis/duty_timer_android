package com.example.duty_timer.screens.singIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentSignInBinding
import com.example.duty_timer.utils.HasProgressBar
import com.example.duty_timer.utils.Pending
import com.example.duty_timer.utils.Result
import com.example.duty_timer.utils.Success

class SignInFragment : Fragment(), HasProgressBar {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel = SignInViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        viewModel.signInResult.observe(viewLifecycleOwner) {
            updateProgressBar(it)
            if (it is Success) findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
        }

        binding.signIn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.signIn(email, password)
        }

        return binding.root
    }

    override fun <T> updateProgressBar(result: Result<T>) {
        binding.root.children.forEach {
            it.visibility = View.INVISIBLE
        }
        when (result) {
            is Success -> binding.root.children.forEach {
                if (it.id != R.id.signInProgressBar) it.visibility = View.INVISIBLE
            }
            is Pending -> binding.signInProgressBar.visibility = View.VISIBLE
            else -> binding.root.children.forEach {
                if (it.id != R.id.signInProgressBar) it.visibility = View.INVISIBLE
            }
        }
    }
}