package com.example.duty_timer.screens.signUp

import android.content.BroadcastReceiver.PendingResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.model.RepositoriesProvider
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.model.timer.TimerRepository
import com.example.duty_timer.utils.MutableLiveResult
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository = RepositoriesProvider.authRepository,
) : ViewModel() {

    private val _signUpResult = MutableLiveResult<Unit?>()
    val signUpResult = _signUpResult

    fun signUp(email: String, password: String, name: String, nickname: String) {
        viewModelScope.launch {
            _signUpResult.value = PendingTask()
            authRepository.signUp(email, password, name, nickname)
            _signUpResult.value = SuccessTask(null)
        }
    }

}