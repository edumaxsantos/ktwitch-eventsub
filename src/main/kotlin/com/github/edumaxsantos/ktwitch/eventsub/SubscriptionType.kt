package com.github.edumaxsantos.ktwitch.eventsub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.java

@Serializable
enum class SubscriptionType {

    /**
     * A user is notified if a message is caught by automod for review.
     * For version 2, only public blocked terms trigger notifications, not private ones.
     */
    @SerialName("automod.message.hold")
    AUTOMOD_MESSAGE_HOLD,

    /**
     * A message in the automod queue had its status changed.
     * For version 2, only public blocked terms trigger notifications, not private ones.
     */
    @SerialName("automod.message.update")
    AUTOMOD_MESSAGE_UPDATE,

    /**
     * A notification is sent when a broadcaster’s automod settings are updated.
     */
    @SerialName("automod.settings.update")
    AUTOMOD_SETTINGS_UPDATE,

    /**
     * A notification is sent when a broadcaster’s automod terms are updated. Changes to private terms are not sent.
     */
    @SerialName("automod.terms.update")
    AUTOMOD_TERMS_UPDATE,

    /**
     * A broadcaster updates their channel properties e.g., category, title, content classification labels, broadcast, or language.
     */
    @SerialName("channel.update")
    CHANNEL_UPDATE,

    /**
     * A specified channel receives a follow.
     */
    @SerialName("channel.follow")
    CHANNEL_FOLLOW,

    /**
     * A midroll commercial break has started running.
     */
    @SerialName("channel.ad_break.begin")
    CHANNEL_AD_BREAK_BEGIN,

    /**
     * A moderator or bot has cleared all messages from the chat room.
     */
    @SerialName("channel.chat.clear")
    CHANNEL_CHAT_CLEAR,

    /**
     * A moderator or bot has cleared all messages from a specific user.
     */
    @SerialName("channel.chat.clear_user_messages")
    CHANNEL_CHAT_CLEAR_USER_MESSAGES,

    /**
     * Any user sends a message to a specific chat room.
     */
    @SerialName("channel.chat.message")
    CHANNEL_CHAT_MESSAGE,

    /**
     * A moderator has removed a specific message.
     */
    @SerialName("channel.chat.message_delete")
    CHANNEL_CHAT_MESSAGE_DELETE,

    /**
     * A notification for when an event that appears in chat has occurred.
     */
    @SerialName("channel.chat.notification")
    CHANNEL_CHAT_NOTIFICATION,

    /**
     * A notification for when a broadcaster’s chat settings are updated.
     */
    @SerialName("channel.chat_settings.update")
    CHANNEL_CHAT_SETTINGS_UPDATE,

    /**
     * A user is notified if their message is caught by automod.
     */
    @SerialName("channel.chat.user_message_hold")
    CHANNEL_CHAT_USER_MESSAGE_HOLD,

    /**
     * A user is notified if their message’s automod status is updated.
     */
    @SerialName("channel.chat.user_message_update")
    CHANNEL_CHAT_USER_MESSAGE_UPDATE,

    /**
     * A notification when a channel becomes active in an active shared chat session.
     */
    @SerialName("channel.shared_chat.begin")
    CHANNEL_SHARED_CHAT_SESSION_BEGIN,

    /**
     * A notification when the active shared chat session the channel is in changes.
     */
    @SerialName("channel.shared_chat.update")
    CHANNEL_SHARED_CHAT_SESSION_UPDATE,

    /**
     * A notification when a channel leaves a shared chat session or the session ends.
     */
    @SerialName("channel.shared_chat.end")
    CHANNEL_SHARED_CHAT_SESSION_END,

    /**
     * A notification is sent when a specified channel receives a subscriber.
     * This does not include resubscribes.
     */
    @SerialName("channel.subscribe")
    CHANNEL_SUBSCRIBE,

    /**
     * A notification when a subscription to the specified channel ends.
     */
    @SerialName("channel.subscription.end")
    CHANNEL_SUBSCRIPTION_END,

    /**
     * A notification when a viewer gives a gift subscription to one or more users
     * in the specified channel.
     */
    @SerialName("channel.subscription.gift")
    CHANNEL_SUBSCRIPTION_GIFT,

    /**
     * A notification when a user sends a resubscription chat message in a specific channel.
     */
    @SerialName("channel.subscription.message")
    CHANNEL_SUBSCRIPTION_MESSAGE,

    /**
     * A user cheers on the specified channel.
     */
    @SerialName("channel.cheer")
    CHANNEL_CHEER,

    /**
     * A broadcaster raids another broadcaster’s channel.
     */
    @SerialName("channel.raid")
    CHANNEL_RAID,

    /**
     * A viewer is banned from the specified channel.
     */
    @SerialName("channel.ban")
    CHANNEL_BAN,

    /**
     * A viewer is unbanned from the specified channel.
     */
    @SerialName("channel.unban")
    CHANNEL_UNBAN,

    /**
     * A user creates an unban request.
     */
    @SerialName("channel.unban_request.create")
    CHANNEL_UNBAN_REQUEST_CREATE,

    /**
     * An unban request has been resolved.
     */
    @SerialName("channel.unban_request.resolve")
    CHANNEL_UNBAN_REQUEST_RESOLVE,

    /**
     * A moderator performs a moderation action in a channel.
     * For version 2, includes warnings.
     */
    @SerialName("channel.moderate")
    CHANNEL_MODERATE,

    /**
     * Moderator privileges were added to a user on a specified channel.
     */
    @SerialName("channel.moderator.add")
    CHANNEL_MODERATOR_ADD,

    /**
     * Moderator privileges were removed from a user on a specified channel.
     */
    @SerialName("channel.moderator.remove")
    CHANNEL_MODERATOR_REMOVE,

    /**
     * The host began a new Guest Star session.
     */
    @SerialName("channel.guest_star_session.begin")
    CHANNEL_GUEST_STAR_SESSION_BEGIN,

    /**
     * A running Guest Star session has ended.
     */
    @SerialName("channel.guest_star_session.end")
    CHANNEL_GUEST_STAR_SESSION_END,

    /**
     * A guest or a slot is updated in an active Guest Star session.
     */
    @SerialName("channel.guest_star_guest.update")
    CHANNEL_GUEST_STAR_GUEST_UPDATE,

    /**
     * The host preferences for Guest Star have been updated.
     */
    @SerialName("channel.guest_star_settings.update")
    CHANNEL_GUEST_STAR_SETTINGS_UPDATE,

    /**
     * A viewer has redeemed an automatic channel points reward on the specified channel.
     */
    @SerialName("channel.channel_points_automatic_reward_redemption.add")
    CHANNEL_POINTS_AUTOMATIC_REWARD_REDEMPTION,

    /**
     * A custom channel points reward has been created for the specified channel.
     */
    @SerialName("channel.channel_points_custom_reward.add")
    CHANNEL_POINTS_CUSTOM_REWARD_ADD,

    /**
     * A custom channel points reward has been updated for the specified channel.
     */
    @SerialName("channel.channel_points_custom_reward.update")
    CHANNEL_POINTS_CUSTOM_REWARD_UPDATE,

    /**
     * A custom channel points reward has been removed from the specified channel.
     */
    @SerialName("channel.channel_points_custom_reward.remove")
    CHANNEL_POINTS_CUSTOM_REWARD_REMOVE,

    /**
     * A viewer has redeemed a custom channel points reward on the specified channel.
     */
    @SerialName("channel.channel_points_custom_reward_redemption.add")
    CHANNEL_POINTS_CUSTOM_REWARD_REDEMPTION_ADD,

    /**
     * A redemption of a channel points custom reward has been updated for the specified channel.
     */
    @SerialName("channel.channel_points_custom_reward_redemption.update")
    CHANNEL_POINTS_CUSTOM_REWARD_REDEMPTION_UPDATE,

    /**
     * A poll begins on a specified channel.
     */
    @SerialName("channel.poll.begin")
    CHANNEL_POLL_BEGIN,

    /**
     * Users respond to a poll on a specified channel.
     */
    @SerialName("channel.poll.progress")
    CHANNEL_POLL_PROGRESS,

    /**
     * A poll ended on a specified channel.
     */
    @SerialName("channel.poll.end")
    CHANNEL_POLL_END,

    /**
     * A Prediction started on a specified channel.
     */
    @SerialName("channel.prediction.begin")
    CHANNEL_PREDICTION_BEGIN,

    /**
     * Users participated in a Prediction on a specified channel.
     */
    @SerialName("channel.prediction.progress")
    CHANNEL_PREDICTION_PROGRESS,

    /**
     * A Prediction was locked on a specified channel.
     */
    @SerialName("channel.prediction.lock")
    CHANNEL_PREDICTION_LOCK,

    /**
     * A Prediction ended on a specified channel.
     */
    @SerialName("channel.prediction.end")
    CHANNEL_PREDICTION_END,

    /**
     * A chat message has been sent by a suspicious user.
     */
    @SerialName("channel.suspicious_user.message")
    CHANNEL_SUSPICIOUS_USER_MESSAGE,

    /**
     * A suspicious user has been updated.
     */
    @SerialName("channel.suspicious_user.update")
    CHANNEL_SUSPICIOUS_USER_UPDATE,

    /**
     * A VIP is added to the channel.
     */
    @SerialName("channel.vip.add")
    CHANNEL_VIP_ADD,

    /**
     * A VIP is removed from the channel.
     */
    @SerialName("channel.vip.remove")
    CHANNEL_VIP_REMOVE,

    /**
     * A user acknowledges a warning. Broadcasters and moderators can see the warning’s details.
     */
    @SerialName("channel.warning.acknowledge")
    CHANNEL_WARNING_ACKNOWLEDGMENT,

    /**
     * A user is sent a warning. Broadcasters and moderators can see the warning’s details.
     */
    @SerialName("channel.warning.send")
    CHANNEL_WARNING_SEND,

    /**
     * Sends an event notification when a user donates to the broadcaster’s charity campaign.
     */
    @SerialName("channel.charity_campaign.donate")
    CHARITY_DONATION,

    /**
     * Sends an event notification when the broadcaster starts a charity campaign.
     */
    @SerialName("channel.charity_campaign.start")
    CHARITY_CAMPAIGN_START,

    /**
     * Sends an event notification when progress is made towards the campaign’s goal or when the broadcaster changes the fundraising goal.
     */
    @SerialName("channel.charity_campaign.progress")
    CHARITY_CAMPAIGN_PROGRESS,

    /**
     * Sends an event notification when the broadcaster stops a charity campaign.
     */
    @SerialName("channel.charity_campaign.stop")
    CHARITY_CAMPAIGN_STOP,

    /**
     * Sends a notification when EventSub disables a shard due to the status of the underlying transport changing.
     */
    @SerialName("conduit.shard.disabled")
    CONDUIT_SHARD_DISABLED,

    /**
     * An entitlement for a Drop is granted to a user.
     */
    @SerialName("drop.entitlement.grant")
    DROP_ENTITLEMENT_GRANT,

    /**
     * A Bits transaction occurred for a specified Twitch Extension.
     */
    @SerialName("extension.bits_transaction.create")
    EXTENSION_BITS_TRANSACTION_CREATE,

    /**
     * Get notified when a broadcaster begins a goal.
     */
    @SerialName("channel.goal.begin")
    GOAL_BEGIN,

    /**
     * Get notified when progress (either positive or negative) is made towards a broadcaster’s goal.
     */
    @SerialName("channel.goal.progress")
    GOAL_PROGRESS,

    /**
     * Get notified when a broadcaster ends a goal.
     */
    @SerialName("channel.goal.end")
    GOAL_END,

    /**
     * A Hype Train begins on the specified channel.
     */
    @SerialName("channel.hype_train.begin")
    HYPE_TRAIN_BEGIN,

    /**
     * A Hype Train makes progress on the specified channel.
     */
    @SerialName("channel.hype_train.progress")
    HYPE_TRAIN_PROGRESS,

    /**
     * A Hype Train ends on the specified channel.
     */
    @SerialName("channel.hype_train.end")
    HYPE_TRAIN_END,

    /**
     * Sends a notification when the broadcaster activates Shield Mode.
     */
    @SerialName("channel.shield_mode.begin")
    SHIELD_MODE_BEGIN,

    /**
     * Sends a notification when the broadcaster deactivates Shield Mode.
     */
    @SerialName("channel.shield_mode.end")
    SHIELD_MODE_END,

    /**
     * Sends a notification when the specified broadcaster sends a Shoutout.
     */
    @SerialName("channel.shoutout.create")
    SHOUTOUT_CREATE,

    /**
     * Sends a notification when the specified broadcaster receives a Shoutout.
     */
    @SerialName("channel.shoutout.receive")
    SHOUTOUT_RECEIVED,

    /**
     * The specified broadcaster starts a stream.
     */
    @SerialName("stream.online")
    STREAM_ONLINE,

    /**
     * The specified broadcaster stops a stream.
     */
    @SerialName("stream.offline")
    STREAM_OFFLINE,

    /**
     * A user’s authorization has been granted to your client id.
     */
    @SerialName("user.authorization.grant")
    USER_AUTHORIZATION_GRANT,

    /**
     * A user’s authorization has been revoked for your client id.
     */
    @SerialName("user.authorization.revoke")
    USER_AUTHORIZATION_REVOKE,

    /**
     * A user has updated their account.
     */
    @SerialName("user.update")
    USER_UPDATE,

    /**
     * A user receives a whisper.
     */
    @SerialName("user.whisper.message")
    WHISPER_RECEIVED();
}

fun findSubscriptionType(value: String): SubscriptionType {
    return SubscriptionType.entries.first { it::class.java.getField(it.name).getAnnotation(SerialName::class.java).value == value }
}

@Serializable
enum class SubscriptionTypeVersion {
    @SerialName("1")
    ONE,

    @SerialName("2")
    TWO,

    @SerialName("beta")
    BETA
}