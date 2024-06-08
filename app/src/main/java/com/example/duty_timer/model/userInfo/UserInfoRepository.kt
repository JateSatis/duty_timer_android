package com.example.duty_timer.model.userInfo

import com.example.duty_timer.BackendException
import com.example.duty_timer.InvalidCredentialsException
import com.example.duty_timer.appSettings.AppSettings
import com.example.duty_timer.model.userInfo.entitites.UserInfo
import com.example.duty_timer.utils.EmptyTask
import com.example.duty_timer.utils.PendingTask
import com.example.duty_timer.utils.ResultTask
import com.example.duty_timer.utils.SuccessTask
import kotlinx.coroutines.flow.MutableStateFlow

class UserInfoRepository(
    private val userInfoSource: UserInfoSource,
    private val appSettings: AppSettings
) {

    private val _userInfoFlow = MutableStateFlow<ResultTask<UserInfo>>(EmptyTask())
    val userInfoFlow = _userInfoFlow

    suspend fun getUserInfo() {
        val token = appSettings.getCurrentToken() ?: return
        _userInfoFlow.value = PendingTask();
        val userInfo = try {
            userInfoSource.getUserInfo(token)
        } catch (e: Exception) {
            if (e is BackendException && e.code == 401) {
                // map 401 error for sign-in to InvalidCredentialsException
                throw InvalidCredentialsException(e)
            } else {
                throw e
            }
        }
        _userInfoFlow.value = SuccessTask(userInfo)
    }
}