package com.example.authenticationver2.data.api

import com.example.authenticationver2.data.api.dto.UserDto
import com.example.authenticationver2.data.api.dto.UserDtoItem
import com.example.authenticationver2.domain.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("user?userEmail={userEmail}")
    suspend fun getAccount(@Path("userEmail") userEmail: String): Call<UserDto>

    @GET("user?token={token}")
    suspend fun identifyAccount(@Path("token") token: String): Call<UserDto>

    @POST("user")
    suspend fun createAccount(@Body user: User): Call<UserDtoItem>
}