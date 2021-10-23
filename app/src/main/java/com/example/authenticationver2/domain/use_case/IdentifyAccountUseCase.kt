package com.example.authenticationver2.domain.use_case

import com.example.authenticationver2.domain.model.User
import com.example.authenticationver2.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.authenticationver2.common.Result
import javax.inject.Inject

class IdentifyAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(token: String): Flow<Result<User>> = userRepository.identifyAccount(token)
}