package com.example.authenticationver2.presentation.signup

import androidx.lifecycle.SavedStateHandle
import com.example.authenticationver2.domain.use_case.CreateAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    savedStateHandle: SavedStateHandle
){
}