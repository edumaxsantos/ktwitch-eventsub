package com.github.edumaxsantos.ktwitch.eventsub.websocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MessageType {
    @SerialName("session_welcome")
    SESSION_WELCOME,

    @SerialName("session_keepalive")
    SESSION_KEEPALIVE,

    @SerialName("session_reconnect")
    SESSION_RECONNECT,

    @SerialName("revocation")
    REVOCATION,

    @SerialName("notification")
    NOTIFICATION,
}