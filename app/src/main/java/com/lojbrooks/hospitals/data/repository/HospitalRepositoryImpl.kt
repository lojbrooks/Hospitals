package com.lojbrooks.hospitals.data.repository

import com.lojbrooks.hospitals.data.mapper.HospitalMapper
import com.lojbrooks.hospitals.data.remote.HospitalApiClient
import com.lojbrooks.hospitals.domain.exception.DataFetchException
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import java.io.IOException
import javax.inject.Inject

class HospitalRepositoryImpl @Inject constructor(
    private val hospitalApiClient: HospitalApiClient,
    private val hospitalMapper: HospitalMapper
) : HospitalRepository {
    override suspend fun getAllHospitals(): Result<List<Hospital>> {
        return try {
            val response = hospitalApiClient.getHospitals()

            if (response.isSuccessful) {
                Result.success(response.body().orEmpty().map { hospitalMapper.toDomain(it) })
            } else {
                Result.failure(DataFetchException())
            }
        } catch (ioException: IOException) {
            Result.failure(DataFetchException())
        }
    }
}