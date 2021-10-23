package com.example.authenticationver2.domain.use_case

import com.example.authenticationver2.domain.model.User
import com.example.authenticationver2.domain.repository.UserRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(user: User) = userRepository.createAccount(user)
}