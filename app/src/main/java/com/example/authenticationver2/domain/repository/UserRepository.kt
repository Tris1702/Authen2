package com.example.authenticationver2.domain.repository

import com.example.authenticationver2.domain.model.User
import com.example.authenticationver2.common.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface UserRepository {
    suspend fun createAccount(user: User) : Flow<Result<User>>
    suspend fun identifyAccount(token: String) : Flow<Result<User>>
}