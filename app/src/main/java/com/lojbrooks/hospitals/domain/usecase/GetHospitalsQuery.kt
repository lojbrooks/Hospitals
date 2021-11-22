package com.lojbrooks.hospitals.domain.usecase

import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import javax.inject.Inject

class GetHospitalsQuery @Inject constructor(private val hospitalRepository: HospitalRepository) {

    suspend operator fun invoke(): Result<List<Hospital>> {
        return hospitalRepository.getAllHospitals()
    }
}