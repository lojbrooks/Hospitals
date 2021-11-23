package com.lojbrooks.hospitals.di

import com.lojbrooks.hospitals.data.local.HospitalDao
import com.lojbrooks.hospitals.data.local.InMemoryHospitalDao
import com.lojbrooks.hospitals.data.repository.HospitalRepositoryImpl
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideHospitalRepository(repo: HospitalRepositoryImpl): HospitalRepository {
        return repo
    }

    @Provides
    @Singleton
    fun provideHospitalDao(dao: InMemoryHospitalDao): HospitalDao {
        return dao
    }
}