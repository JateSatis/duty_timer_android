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
import com.example.duty_timer.utils.HasProgressBar
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import com.example.duty_timer.utils.findTopNavController
import com.example.duty_timer.utils.viewModelCreator

class AccountFragment : Fragment(), HasProgressBar {
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

        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().navigateUp()
        }

        viewModel.getUserInfo()

        viewModel.accountInfoResult.observe(viewLifecycleOwner) {
            updateProgressBar(it);
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

        binding.logout.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(R.id.action_accountFragment_to_roleFragment)
        }

        return binding.root
    }

    override fun <T> updateProgressBar(result: ResultTask<T>) {
        Log.d("TABS", "Hello")
        binding.root.children.forEach {
            it.visibility = View.INVISIBLE
        }
        when (result) {
            is SuccessTask -> {
                binding.root.children.forEach {
                    if (it.id != R.id.accountProgressBar) it.visibility = View.VISIBLE
                }
            }
            is PendingTask -> binding.accountProgressBar.visibility = View.VISIBLE
            else -> {
                binding.root.children.forEach {
                    if (it.id != R.id.accountProgressBar) it.visibility = View.VISIBLE
                }
            }
        }
    }


}