package io.github.edumaxsantos.ktwitch.eventsub.websocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SubscriptionStatus {
    @SerialName("user_removed")
    USER_REMOVED,
    @SerialName("authorization_revoked")
    AUTHORIZATION_REVOKED,
    @SerialName("version_removed")
    VERSION_REMOVED,
    @SerialName("enabled")
    ENABLED
}