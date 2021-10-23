package com.example.authenticationver2.domain.model

data class User(
    val id: Int,
    val token: String,
    val userEmail: String,
    val userHashPassword: String,
    val userName: String
)
