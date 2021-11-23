package com.lojbrooks.hospitals.domain.model

data class Hospital(
    val orgId: Int,
    val name: String,
    val orgCode: String,
    val sector: Sector,
    val address: Address,
    val contactDetails: ContactDetails
)

data class Address(
    val address1: String?,
    val address2: String?,
    val address3: String?,
    val city: String?,
    val county: String?,
    val postcode: String?
)

data class ContactDetails(
    val email: String?,
    val phone: String?,
    val website: String?
)

enum class Sector {
    NHS, INDEPENDENT, OTHER
}