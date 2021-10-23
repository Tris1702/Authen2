package com.example.authenticationver2.di

import com.example.authenticationver2.common.Constant
import com.example.authenticationver2.data.api.UserApi
import com.example.authenticationver2.data.repository.UserRepositoryImpl
import com.example.authenticationver2.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.attribute.UserPrincipal
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserApi(): UserApi{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository{
        return UserRepositoryImpl(api)
    }
}