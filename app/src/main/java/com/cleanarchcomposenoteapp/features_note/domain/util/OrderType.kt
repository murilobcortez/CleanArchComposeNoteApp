package com.cleanarchcomposenoteapp.features_note.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}