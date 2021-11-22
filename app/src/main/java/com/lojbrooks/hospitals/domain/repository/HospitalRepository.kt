package com.lojbrooks.hospitals.domain.repository

import com.lojbrooks.hospitals.domain.model.Hospital

interface HospitalRepository {
    suspend fun getAllHospitals(): Result<List<Hospital>>
}