package com.github.edumaxsantos.ktwitch.eventsub.websocket.event

import com.github.edumaxsantos.ktwitch.eventsub.SubType
import com.github.edumaxsantos.ktwitch.eventsub.SubscriptionType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SubType(SubscriptionType.STREAM_ONLINE)
@Serializable
data class StreamOnlineEvent(
    /**
     * The id of the stream.
     */
    val id: String,
    /**
     * The broadcaster’s user id.
     */
    val broadcasterUserId: String,
    /**
     * The broadcaster’s user login.
     */
    val broadcasterUserLogin: String,
    /**
     * The broadcaster’s user display name.
     */
    val broadcasterUserName: String,
    /**
     * The stream type. Valid values are: live, playlist, watch_party, premiere, rerun.
     */
    val type: OnlineType,
    /**
     * The timestamp at which the stream went online at.
     */
    val startedAt: String,
) : Event()


@Serializable
enum class OnlineType {
    @SerialName("live")
    LIVE,

    @SerialName("playlist")
    PLAYLIST,

    @SerialName("watch_party")
    WATCH_PARTY,

    @SerialName("premiere")
    PREMIERE,

    @SerialName("rerun")
    RERUN,
}