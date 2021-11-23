package com.lojbrooks.hospitals.data.local

import com.lojbrooks.hospitals.domain.model.Hospital

interface HospitalDao {
    fun getAllHospitals(): List<Hospital>
    fun insert(hospitals: List<Hospital>)
}