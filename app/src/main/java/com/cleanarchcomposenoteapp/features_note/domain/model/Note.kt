package com.cleanarchcomposenoteapp.features_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cleanarchcomposenoteapp.ui.theme.BabyBlue
import com.cleanarchcomposenoteapp.ui.theme.LightGreen
import com.cleanarchcomposenoteapp.ui.theme.RedOrange
import com.cleanarchcomposenoteapp.ui.theme.RedPink
import com.cleanarchcomposenoteapp.ui.theme.Violet

@Entity
data class Note (
    val title: String? = null,
    val content: String? = null,
    val timestamp: Long? = null,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)