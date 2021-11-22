package com.lojbrooks.hospitals.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface HospitalApiClient {
    @GET("hospitals.json")
    suspend fun getHospitals() : Response<List<HospitalDto>>
}