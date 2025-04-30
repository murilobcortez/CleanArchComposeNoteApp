package com.cleanarchcomposenoteapp.features_note.domain.util

internal sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}