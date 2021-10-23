package com.example.authenticationver2.common

object Constant {
    const val BASE_URL = "https://192.168.21.101/"
    enum class ACCOUNTSTATE(
        USED: Int = 0,
        AVAILABLE: Int = 1,
        CORRECT: Int = 2,
        WRONG: Int = 3
    )
}