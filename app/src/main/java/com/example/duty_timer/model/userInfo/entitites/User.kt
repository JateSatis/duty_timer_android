package com.example.duty_timer.model.userInfo.entitites

data class User(
    val avatar_link: Any?,
    val id: Int,
    val login: String,
    val name: String,
    val nickname: String,
    val online: Boolean,
    val password_hash: String,
    val password_salt: String,
    val user_type: String
)