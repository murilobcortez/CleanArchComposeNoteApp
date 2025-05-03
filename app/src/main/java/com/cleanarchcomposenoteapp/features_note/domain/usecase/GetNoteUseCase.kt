package com.cleanarchcomposenoteapp.features_note.domain.usecase

import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}