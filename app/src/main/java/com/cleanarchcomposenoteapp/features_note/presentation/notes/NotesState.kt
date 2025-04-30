package com.cleanarchcomposenoteapp.features_note.presentation.notes

import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.util.NoteOrder
import com.cleanarchcomposenoteapp.features_note.domain.util.OrderType

internal data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)