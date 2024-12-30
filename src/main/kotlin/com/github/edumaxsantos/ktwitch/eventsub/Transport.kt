package com.github.edumaxsantos.ktwitch.eventsub

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("method")
sealed class Transport {

    @Serializable
    @SerialName("webhook")
    data class Webhook(val callback: String, val secret: String) : Transport()

    @Serializable
    @SerialName("websocket")
    data class Websocket(val sessionId: String? = null) : Transport()

    @Serializable
    @SerialName("conduit")
    data class Conduit(val conduitId: String) : Transport()
}