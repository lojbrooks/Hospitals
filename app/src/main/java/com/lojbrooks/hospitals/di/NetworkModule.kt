package com.lojbrooks.hospitals.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lojbrooks.hospitals.data.remote.HospitalApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://draysontechnologies.github.io/shtest.github.io/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideHospitalApiClient(retrofit: Retrofit): HospitalApiClient {
        return retrofit.create(HospitalApiClient::class.java)
    }

}