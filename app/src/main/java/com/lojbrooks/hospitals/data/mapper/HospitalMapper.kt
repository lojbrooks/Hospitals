package com.lojbrooks.hospitals.data.mapper

import com.lojbrooks.hospitals.data.remote.HospitalDto
import com.lojbrooks.hospitals.domain.model.Hospital
import javax.inject.Inject

class HospitalMapper @Inject constructor() {

    fun toDomain(dto: HospitalDto): Hospital {
        return Hospital(
            name = dto.orgName,
            orgCode = dto.orgCode.string ?: dto.orgCode.int.toString()
        )
    }
}