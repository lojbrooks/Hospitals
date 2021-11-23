package com.lojbrooks.hospitals.data.repository

import com.lojbrooks.hospitals.data.local.HospitalDao
import com.lojbrooks.hospitals.data.mapper.HospitalMapper
import com.lojbrooks.hospitals.data.remote.HospitalApiClient
import com.lojbrooks.hospitals.domain.exception.DataFetchException
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import java.io.IOException
import javax.inject.Inject

class HospitalRepositoryImpl @Inject constructor(
    private val hospitalApiClient: HospitalApiClient,
    private val hospitalMapper: HospitalMapper,
    private val hospitalDao: HospitalDao
) : HospitalRepository {
    override suspend fun getAllHospitals(): Result<List<Hospital>> {
        val cachedHospitals = hospitalDao.getAllHospitals()
        return if (cachedHospitals.isNotEmpty()) {
            Result.success(cachedHospitals)
        } else fetchHospitalsFromRemoteDataSource()

    }

    private suspend fun fetchHospitalsFromRemoteDataSource(): Result<List<Hospital>> {
        return try {
            val response = hospitalApiClient.getHospitals()

            if (response.isSuccessful) {
                val hospitals = response.body().orEmpty().map { hospitalMapper.toDomain(it) }
                hospitalDao.insert(hospitals)
                Result.success(hospitals)
            } else {
                Result.failure(DataFetchException())
            }
        } catch (ioException: IOException) {
            Result.failure(DataFetchException())
        }
    }
}