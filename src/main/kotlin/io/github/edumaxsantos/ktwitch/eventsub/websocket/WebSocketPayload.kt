package io.github.edumaxsantos.ktwitch.eventsub.websocket

import io.github.edumaxsantos.ktwitch.eventsub.websocket.event.Event
import io.github.edumaxsantos.ktwitch.eventsub.SubscriptionType
import io.github.edumaxsantos.ktwitch.eventsub.SubscriptionTypeVersion
import io.github.edumaxsantos.ktwitch.eventsub.Transport
import kotlinx.serialization.Serializable
import java.util.concurrent.locks.Condition


@Serializable(with = WebSocketPayloadSerializer::class)
sealed class WebSocketPayload {
    @Serializable
    data class SessionWelcomePayload(
        val session: Session
    ) : WebSocketPayload()

    @Serializable
    data class SubscriptionEventPayload(
        val subscription: Subscription,
        val event: Event,
    ) : WebSocketPayload()

    @Serializable
    data class SubscriptionRevocationPayload(
        val subscription: Subscription,
    ) : WebSocketPayload()

    @Serializable
    object EmptyPayload : WebSocketPayload()

    @Serializable
    data class ReconnectSessionPayload(
        val session: Session
    ) : WebSocketPayload()
}

@Serializable
data class Subscription(val id: String,
                        val status: SubscriptionStatus,
                        val type: SubscriptionType,
                        val version: SubscriptionTypeVersion,
                        val cost: Int,
                        val condition: Map<Condition, String>,
                        val transport: Transport,
                        val createdAt: String,)

@Serializable
data class Session(val id: String,
                   val status: SessionStatus,
                   val keepaliveTimeoutSeconds: Int?,
                   val reconnectUrl: String? = null,
                   val connectedAt: String,)