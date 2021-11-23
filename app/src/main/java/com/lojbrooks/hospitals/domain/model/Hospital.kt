package com.lojbrooks.hospitals.domain.model

data class Hospital(val name: String, val orgCode: String, val sector: Sector)

enum class Sector {
    NHS, INDEPENDENT, OTHER
}