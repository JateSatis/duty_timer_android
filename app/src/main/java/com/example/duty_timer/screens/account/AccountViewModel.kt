package com.example.duty_timer.screens.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duty_timer.globals.Singletons
import com.example.duty_timer.model.auth.AuthRepository
import com.example.duty_timer.model.userInfo.UserInfoRepository
import com.example.duty_timer.model.userInfo.entitites.UserInfo
import com.example.duty_timer.utils.MutableLiveResult
import kotlinx.coroutines.launch

class AccountViewModel(
    private val userInfoRepository: UserInfoRepository = Singletons.userInfoRepository,
    private val authRepository: AuthRepository = Singletons.authRepository
) : ViewModel() {

    private val _accountInfoResult = MutableLiveResult<UserInfo>()
    val accountInfoResult = _accountInfoResult

    init {
        viewModelScope.launch {
            userInfoRepository.userInfoFlow.collect { userInfo ->
                _accountInfoResult.value = userInfo
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            userInfoRepository.getUserInfo()
        }
    }

    fun logOut() {
        viewModelScope.launch {
            authRepository.logOut()
        }
    }
}