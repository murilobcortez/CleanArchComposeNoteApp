package com.cleanarchcomposenoteapp.features_note.presentation.notes

import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.util.NoteOrder

sealed class NotesAction {
    data class Order(val noteOrder: NoteOrder) : NotesAction()
    data class DeleteNote(val note: Note) : NotesAction()
    data object RestoreNote : NotesAction()
    data object ToggleOrderSection : NotesAction()
}