package com.lojbrooks.hospitals.data.local

import com.lojbrooks.hospitals.domain.model.Hospital
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryHospitalDao @Inject constructor() : HospitalDao {
    private val hospitals = mutableListOf<Hospital>()

    override fun getHospital(orgId: Int): Hospital {
        return hospitals.first {it.orgId == orgId}
    }

    override fun getAllHospitals(): List<Hospital> {
        return hospitals
    }

    override fun insert(hospitals: List<Hospital>) {
        this.hospitals.addAll(hospitals)
    }
}