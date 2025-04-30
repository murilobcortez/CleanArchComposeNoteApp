package com.cleanarchcomposenoteapp.features_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cleanarchcomposenoteapp.ui.theme.RedOrange

@Entity
data class Note (
    val title: String? = null,
    val content: String? = null,
    val timestamp: Long? = null,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange)
    }
}

class InvalidNoteException(message: String) : Exception(message)