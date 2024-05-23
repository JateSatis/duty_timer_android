package com.example.duty_timer.screens.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.utils.MutableLiveResult
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository = Singletons.authRepository) : ViewModel() {

    private val _signInResult = MutableLiveResult<Unit?>()
    val signInResult = _signInResult

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInResult.value = PendingTask()
        authRepository.signIn(email, password);
        _signInResult.value = SuccessTask(null)
    }

}