package com.example.authenticationver2.data.api.dto

import com.example.authenticationver2.domain.model.User

data class UserDtoItem(
    val id: Int,
    val token: String,
    val userEmail: String,
    val userHashPassword: String,
    val userName: String
)
fun UserDtoItem.toUser() : User{
    return User(
        id = id,
        token = token,
        userEmail = userEmail,
        userHashPassword = userHashPassword,
        userName = userName
    )
}