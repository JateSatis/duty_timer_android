package com.example.duty_timer.screens.signIn

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
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.viewModelCreator

class SignInFragment : Fragment(), HasProgressBar {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel by viewModelCreator { SignInViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signInResult.observe(viewLifecycleOwner) {
            updateProgressBar(it)
            if (it is SuccessTask) findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
        }

        binding.signIn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.signIn(email, password)
        }
    }

    override fun <T> updateProgressBar(result: ResultTask<T>) {
        binding.root.children.forEach {
            it.visibility = View.INVISIBLE
        }
        when (result) {
            is SuccessTask -> binding.root.children.forEach {
                if (it.id != R.id.signInProgressBar) it.visibility = View.INVISIBLE
            }
            is PendingTask -> binding.signInProgressBar.visibility = View.VISIBLE
            else -> binding.root.children.forEach {
                if (it.id != R.id.signInProgressBar) it.visibility = View.INVISIBLE
            }
        }
    }
}