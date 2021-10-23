package com.example.authenticationver2.data.repository

import com.example.authenticationver2.domain.model.User
import com.example.authenticationver2.domain.repository.UserRepository
import com.example.authenticationver2.common.Result
import com.example.authenticationver2.data.api.UserApi
import com.example.authenticationver2.data.api.dto.UserDto
import com.example.authenticationver2.data.api.dto.UserDtoItem
import com.example.authenticationver2.data.api.dto.toUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
): UserRepository{
    override suspend fun createAccount(user: User): Flow<Result<User>> = flow{
        emit(Result.Loading)
        try{
            checkIfAccountExist(user.userEmail){
                when (it){
                    0->{
                        suspend {
                            userApi.createAccount(user).enqueue(object: Callback<UserDtoItem>{
                                override fun onResponse(
                                    call: Call<UserDtoItem>,
                                    response: Response<UserDtoItem>
                                ) {
                                    suspend { emit(Result.Success((response.body() as UserDtoItem).toUser())) }
                                }

                                override fun onFailure(call: Call<UserDtoItem>, t: Throwable) {
                                    suspend { emit(Result.Error("Couldn't reach server. Check your internet connection")) }
                                }

                            })
                        }
                    }
                    1->{
                        suspend { emit(Result.Error("This email has been used, please sign up a new account")) }
                    }
                    2->{
                        suspend { emit(Result.Error("Couldn't reach server. Check your internet connection")) }
                    }
                }
            }
        }catch (e: HttpException) {
            emit(Result.Error(e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Result.Error("Couldn't reach server. Check your internet connection"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun identifyAccount(token: String): Flow<Result<User>> = flow{
        emit(Result.Loading)
        try{
            userApi.identifyAccount(token).enqueue(object: Callback<UserDto>{
                override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                    val listUser = (response.body() as UserDto)
                    if (listUser.isEmpty()){
                        suspend { emit(Result.Error("Wrong token, please login again")) }
                    }
                    else{
                        suspend{ emit(Result.Success(listUser.first().toUser())) }
                    }
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    suspend {
                        emit(Result.Error("Couldn't reach server. Check your internet connection"))
                    }
                }

            })
        }catch (e: HttpException){
            emit(Result.Error(e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException){
            emit(Result.Error("Couldn't reach server. Check your internet connection"))
        }

    }.flowOn(Dispatchers.IO)

    suspend fun checkIfAccountExist(userEmail: String, returnValue: (Int) -> Unit) {
        userApi.getAccount(userEmail).enqueue(object: Callback<UserDto>{
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                returnValue( (response.body() as UserDto).size )
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                suspend {
                    returnValue(2)
                }
            }

        })
    }
}