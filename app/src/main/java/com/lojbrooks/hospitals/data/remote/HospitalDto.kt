package com.lojbrooks.hospitals.data.remote

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable
data class HospitalDto(
    @SerialName("OrganisationID") val orgId: Int,
    @SerialName("OrganisationCode") val orgCode: StringOrInt,
    @SerialName("Sector") val sector: String,
    @SerialName("OrganisationName") val orgName: String,
    @SerialName("Address1") val address1: String,
    @SerialName("Address2") val address2: String,
    @SerialName("Address3") val address3: String,
    @SerialName("City") val city: String,
    @SerialName("County") val county: String,
    @SerialName("Postcode") val postcode: String,
    @SerialName("Phone") val phone: String,
    @SerialName("Email") val email: String,
    @SerialName("Website") val website: String
)

@Serializable(with = StringOrIntSerializer::class)
data class StringOrInt(val string: String?, val int: Int?)

object StringOrIntSerializer : KSerializer<StringOrInt> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("StringOrInt", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): StringOrInt {
        val json = (decoder as JsonDecoder).decodeJsonElement().jsonPrimitive
        return if(json.isString) {
            StringOrInt(json.content, null)
        } else {
            StringOrInt(null, json.int)
        }
    }

    override fun serialize(encoder: Encoder, value: StringOrInt) {
        throw IllegalAccessException("serialization not required")
    }
}