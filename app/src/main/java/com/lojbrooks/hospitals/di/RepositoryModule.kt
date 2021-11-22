package com.lojbrooks.hospitals.di

import com.lojbrooks.hospitals.data.repository.HospitalRepositoryImpl
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideHospitalRepository(repo: HospitalRepositoryImpl): HospitalRepository {
        return repo
    }
}