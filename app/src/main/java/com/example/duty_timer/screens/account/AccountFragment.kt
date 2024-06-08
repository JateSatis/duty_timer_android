package com.example.duty_timer.screens.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.duty_timer.R
import com.example.duty_timer.databinding.FragmentAccountBinding
import com.example.duty_timer.utils.FragmentWithProgressBar
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.viewModelCreator

class AccountFragment : FragmentWithProgressBar() {
    private lateinit var binding: FragmentAccountBinding
    private val viewModel by viewModelCreator {
        AccountViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        viewModel.getUserInfo()

        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigateUp()
        }

        viewModel.accountInfoResult.observe(viewLifecycleOwner) {
            updateProgressBar(it, binding.accountProgressBar);
            if (it is SuccessTask) {
                val user = it.value.user
                binding.apply {
                    nameTextView.text = user.name
                    nicknameTextView.text = user.nickname
                    emailTextView.text = user.login
                    roleTextView.text = user.user_type
                }
            }
        }

        binding.goBackAccountButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.logout.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(R.id.action_accountFragment_to_newSoldierFragment)
        }

        return binding.root
    }

}