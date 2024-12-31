package io.github.edumaxsantos.ktwitch.eventsub.websocket.event

import io.github.edumaxsantos.ktwitch.eventsub.SubType
import io.github.edumaxsantos.ktwitch.eventsub.SubscriptionType
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
sealed class Event {
    companion object {
        fun getSubclass(type: SubscriptionType): KClass<out Event>? {
            return Event::class.sealedSubclasses.firstOrNull { subclass ->
                subclass.annotations.filterIsInstance<SubType>().any { it.value == type }
            }
        }
    }
}