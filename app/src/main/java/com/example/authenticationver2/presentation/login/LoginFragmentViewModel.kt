package com.example.authenticationver2.presentation.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.authenticationver2.domain.use_case.CreateAccountUseCase
import com.example.authenticationver2.domain.use_case.IdentifyAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val identifyAccountUseCase: IdentifyAccountUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

}