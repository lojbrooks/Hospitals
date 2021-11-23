package com.lojbrooks.hospitals.di

import com.lojbrooks.hospitals.data.remote.HospitalApiClient
import com.lojbrooks.hospitals.fakes.FakeHospitalApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule {

    @Provides
    @Singleton
    fun provideHospitalApiClient(client: FakeHospitalApiClient): HospitalApiClient {
        return client
    }
}