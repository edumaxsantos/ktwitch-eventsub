package io.github.edumaxsantos.ktwitch.eventsub

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class SubType(val value: SubscriptionType)