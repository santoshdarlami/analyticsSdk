package com.darlami.analyticsdk.model

data class Event(
    val eventName: String,
    val params: Map<String, String>,
)

enum class EventType(val eventName: String) {
    USER_PROPERTY("user_property"),
    SCREEN_VIEW("screen_view"),
    PRODUCT_VIEW("product_view"),
    ADD_TO_CART("add_to_cart"),
    PURCHASE("purchase"),
}