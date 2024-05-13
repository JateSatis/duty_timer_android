package com.example.duty_timer.screens.singIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.utils.MutableLiveResult
import com.example.duty_timer.utils.Pending
import com.example.duty_timer.utils.Success
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository = Singletons.authRepository) : ViewModel() {

    private val _signInResult = MutableLiveResult<Unit?>()
    val signInResult = _signInResult

    fun signIn(email: String, password: String) = viewModelScope.launch {
        _signInResult.value = Pending()
        authRepository.signIn(email, password);
        _signInResult.value = Success(null)
    }

}