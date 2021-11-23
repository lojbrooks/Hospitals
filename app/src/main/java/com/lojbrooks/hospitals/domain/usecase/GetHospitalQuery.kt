package com.lojbrooks.hospitals.domain.usecase

import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.repository.HospitalRepository
import javax.inject.Inject

class GetHospitalQuery @Inject constructor(private val hospitalRepository: HospitalRepository) {

    operator fun invoke(orgId: Int): Hospital {
        return hospitalRepository.getHospital(orgId)
    }
}