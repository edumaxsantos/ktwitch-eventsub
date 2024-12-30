package com.github.edumaxsantos.ktwitch.eventsub.websocket

import com.github.edumaxsantos.ktwitch.eventsub.SubscriptionType
import com.github.edumaxsantos.ktwitch.eventsub.SubscriptionTypeVersion
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator


@Serializable
data class WebSocketResponse(val metadata: WebSocketMetadata, val payload: WebSocketPayload)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("message_type")
sealed class WebSocketMetadata {
    abstract val messageType: MessageType

    @Serializable
    @SerialName("session_welcome")
    data class SessionWelcomeMetadata(val messageId: String, override val messageType: MessageType = MessageType.SESSION_WELCOME, val messageTimestamp: String) : WebSocketMetadata()

    @Serializable
    @SerialName("session_keepalive")
    data class SessionKeepaliveMetadata(val messageId: String, override val messageType: MessageType = MessageType.SESSION_KEEPALIVE, val messageTimestamp: String) : WebSocketMetadata()

    @Serializable
    @SerialName("session_reconnect")
    data class SessionReconnectMetadata(val messageId: String, override val messageType: MessageType = MessageType.SESSION_RECONNECT, val messageTimestamp: String) : WebSocketMetadata()

    @Serializable
    @SerialName("revocation")
    data class RevocationMetadata(
        val messageId: String,
        override val messageType: MessageType = MessageType.REVOCATION,
        val messageTimestamp: String,
        val subscriptionType: SubscriptionType,
        val subscriptionVersion: SubscriptionTypeVersion,
    ) : WebSocketMetadata()

    @Serializable
    @SerialName("notification")
    data class NotificationMetadata(
        val messageId: String,
        override val messageType: MessageType = MessageType.NOTIFICATION,
        val messageTimestamp: String,
        val subscriptionType: SubscriptionType,
        val subscriptionVersion: SubscriptionTypeVersion,
    ) : WebSocketMetadata()
}