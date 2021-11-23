package com.lojbrooks.hospitals.fakes

import com.lojbrooks.hospitals.data.remote.HospitalApiClient
import com.lojbrooks.hospitals.data.remote.HospitalDto
import com.lojbrooks.hospitals.data.remote.StringOrInt
import com.lojbrooks.hospitals.domain.model.Sector
import kotlinx.serialization.SerialName
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeHospitalApiClient @Inject constructor() : HospitalApiClient {
    var getHospitalsResponse: Response<List<HospitalDto>> = DEFAULT_HOSPITALS_RESPONSE

    override suspend fun getHospitals(): Response<List<HospitalDto>> {
        return getHospitalsResponse
    }

    companion object {
        val DEFAULT_HOSPITALS_RESPONSE: Response<List<HospitalDto>> = Response.success(listOf(
            HospitalDto(
                orgId = 1,
                orgCode = StringOrInt("HOS1", null),
                sector = "NHS Sector",
                orgName = "Hospital 1",
                address1 = "123 Hospital Street",
                address2 = "",
                address3 = "",
                city = "Worthing",
                county = "West Sussex",
                postcode = "BN1 1AA",
                phone = "0123456789",
                email = "email@test.com",
                website = "www.test.com"
            ),
            HospitalDto(
                orgId = 2,
                orgCode = StringOrInt("HOS2", null),
                sector = "Independent Sector",
                orgName = "Hospital 2",
                address1 = "",
                address2 = "Independent Lane",
                address3 = "",
                city = "Leicester",
                county = "Leicestershire",
                postcode = "LE1 1AA",
                phone = "0123456789",
                email = "email@test.com",
                website = "www.test.com"
            )
        ))
    }
}