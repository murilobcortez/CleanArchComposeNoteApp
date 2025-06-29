package com.cleanarchcomposenoteapp.features_note.domain.usecase

import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        return repository.deleteNote(note)
    }
}