package com.cleanarchcomposenoteapp.features_note.domain.usecase

import com.cleanarchcomposenoteapp.features_note.domain.model.InvalidNoteException
import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

private const val INVALID_TITLE_NOTE_EXCEPTION_MESSAGE = "The title of the note can't be empty"
private const val INVALID_CONTENT_NOTE_EXCEPTION_MESSAGE = "The content of the note can't be empty"

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){

        if(note.title.isNullOrBlank()){
            throw InvalidNoteException(INVALID_TITLE_NOTE_EXCEPTION_MESSAGE)
        }
        if(note.content.isNullOrBlank()){
            throw InvalidNoteException(INVALID_CONTENT_NOTE_EXCEPTION_MESSAGE)
        }

        repository.insertNote(note)
    }
}