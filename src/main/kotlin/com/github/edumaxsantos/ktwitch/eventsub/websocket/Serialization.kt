package com.github.edumaxsantos.ktwitch.eventsub.websocket

import com.github.edumaxsantos.ktwitch.eventsub.SubscriptionType
import com.github.edumaxsantos.ktwitch.eventsub.websocket.event.Event
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.nullable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializerOrNull
import kotlin.collections.forEach
import kotlin.reflect.full.memberProperties
import kotlin.text.lowercase
import kotlin.text.replace

object WebSocketPayloadSerializer : KSerializer<WebSocketPayload> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("WebSocketPayload") {

        element("session", Session.serializer().descriptor.nullable)
        element("subscription", Subscription.serializer().descriptor.nullable)
        element("event", Event.serializer().descriptor.nullable)
    }

    override fun serialize(encoder: Encoder, value: WebSocketPayload) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("This serializer only works with Json")

        val jsonObject = when (value) {
            is WebSocketPayload.SubscriptionEventPayload -> {
                // Delegate to SubscriptionEventPayloadSerializer for custom behavior
                SubscriptionEventPayloadSerializer.toJsonObject(value, jsonEncoder)
            }
            is WebSocketPayload.SessionWelcomePayload -> {
                jsonEncoder.json.encodeToJsonElement(WebSocketPayload.SessionWelcomePayload.serializer(), value).jsonObject
            }
            is WebSocketPayload.SubscriptionRevocationPayload -> {
                jsonEncoder.json.encodeToJsonElement(WebSocketPayload.SubscriptionRevocationPayload.serializer(), value).jsonObject
            }
            is WebSocketPayload.ReconnectSessionPayload -> {
                jsonEncoder.json.encodeToJsonElement(WebSocketPayload.ReconnectSessionPayload.serializer(), value).jsonObject
            }
            WebSocketPayload.EmptyPayload -> JsonObject(emptyMap())
        }

        jsonEncoder.encodeJsonElement(jsonObject)
    }

    override fun deserialize(decoder: Decoder): WebSocketPayload {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer only works with Json")

        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject

        return when {
            jsonObject.isEmpty() -> WebSocketPayload.EmptyPayload
            "session" in jsonObject -> {
                val session = jsonObject["session"]!!.jsonObject
                if ("reconnect_url" in session && session["reconnect_url"] != JsonNull) {
                    jsonDecoder.json.decodeFromJsonElement(WebSocketPayload.ReconnectSessionPayload.serializer(), jsonObject)
                } else {
                    jsonDecoder.json.decodeFromJsonElement(WebSocketPayload.SessionWelcomePayload.serializer(), jsonObject)
                }
            }

            "event" in jsonObject -> {
                jsonDecoder.json.decodeFromJsonElement(SubscriptionEventPayloadSerializer, jsonObject)
            }
            "subscription" in jsonObject -> {
                jsonDecoder.json.decodeFromJsonElement(WebSocketPayload.SubscriptionRevocationPayload.serializer(), jsonObject)
            }
            else -> throw SerializationException("Unknown payload format: $jsonObject")
        }
    }
}


object SubscriptionEventPayloadSerializer : KSerializer<WebSocketPayload.SubscriptionEventPayload> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SubscriptionEventPayload") {
        element("subscription", Subscription.serializer().descriptor)
        element("event", JsonElement.serializer().descriptor)
    }

    override fun serialize(encoder: Encoder, value: WebSocketPayload.SubscriptionEventPayload) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("This serializer only works with Json")

        val jsonObject = toJsonObject(value, jsonEncoder)
        jsonEncoder.encodeJsonElement(jsonObject)
    }

    override fun deserialize(decoder: Decoder): WebSocketPayload.SubscriptionEventPayload {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer only works with Json")

        val jsonObject = jsonDecoder.decodeJsonElement().jsonObject
        val subscription = jsonDecoder.json.decodeFromJsonElement(Subscription.serializer(), jsonObject["subscription"]!!)
        val event = deserializeEvent(subscription.type, jsonObject["event"]!!, jsonDecoder)

        return WebSocketPayload.SubscriptionEventPayload(subscription, event)
    }

    @OptIn(InternalSerializationApi::class)
    fun toJsonObject(value: WebSocketPayload.SubscriptionEventPayload, jsonEncoder: JsonEncoder): JsonObject {
        val subscriptionJson = jsonEncoder.json.encodeToJsonElement(Subscription.serializer(), value.subscription)

        val eventJson = buildJsonObject {
            value.event::class.memberProperties.forEach { property ->
                val propertyName = property.name
                val propertyValue = property.getter.call(value.event) ?: JsonNull
                val propertyJson = when (propertyValue) {
                    is String -> JsonPrimitive(propertyValue)
                    is Int -> JsonPrimitive(propertyValue)
                    is Long -> JsonPrimitive(propertyValue)
                    is Boolean -> JsonPrimitive(propertyValue)
                    is Float -> JsonPrimitive(propertyValue)
                    is Double -> JsonPrimitive(propertyValue)
                    else -> throw IllegalArgumentException("Unsupported type: ${propertyValue::class}")
                }
                put(propertyName.camelToSnakeCase(), propertyJson)
            }
        }


        return buildJsonObject {
            put("subscription", subscriptionJson)
            put("event", eventJson)
        }
    }

    @OptIn(InternalSerializationApi::class)
    private fun deserializeEvent(type: SubscriptionType, eventJson: JsonElement, jsonDecoder: JsonDecoder): Event {
        val eventClass = Event.getSubclass(type) ?: throw SerializationException("Unknown event type: $type")

        val serializer = eventClass.serializerOrNull()
            ?: throw SerializationException("Serializer not found for type: $type")

        return jsonDecoder.json.decodeFromJsonElement(serializer, eventJson)
    }
}

fun String.camelToSnakeCase(): String {
    return this.replace(Regex("([a-z])([A-Z])")) { matchResult ->
        "${matchResult.groupValues[1]}_${matchResult.groupValues[2].lowercase()}"
    }
}