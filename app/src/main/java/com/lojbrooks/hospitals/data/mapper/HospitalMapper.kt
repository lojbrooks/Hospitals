package com.lojbrooks.hospitals.data.mapper

import com.lojbrooks.hospitals.data.remote.HospitalDto
import com.lojbrooks.hospitals.domain.model.Address
import com.lojbrooks.hospitals.domain.model.ContactDetails
import com.lojbrooks.hospitals.domain.model.Hospital
import com.lojbrooks.hospitals.domain.model.Sector
import javax.inject.Inject

class HospitalMapper @Inject constructor() {

    fun toDomain(dto: HospitalDto): Hospital {
        return Hospital(
            orgId = dto.orgId,
            name = dto.orgName,
            orgCode = dto.orgCode.string ?: dto.orgCode.int.toString(),
            sector = when(dto.sector) {
                "NHS Sector" -> Sector.NHS
                "Independent Sector" -> Sector.INDEPENDENT
                else -> Sector.OTHER
            },
            address = Address(
                address1 = dto.address1.nullIfBlank(),
                address2 = dto.address2.nullIfBlank(),
                address3 = dto.address3.nullIfBlank(),
                city = dto.city.nullIfBlank(),
                county = dto.county.nullIfBlank(),
                postcode = dto.postcode.nullIfBlank(),
            ),
            contactDetails = ContactDetails(
                email = dto.email.nullIfBlank(),
                phone = dto.phone.nullIfBlank(),
                website = dto.website.nullIfBlank()
            )
        )
    }

    private fun String.nullIfBlank(): String? {
        return if(isBlank()) null else this
    }
}