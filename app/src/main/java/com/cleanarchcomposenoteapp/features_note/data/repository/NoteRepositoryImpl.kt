package com.cleanarchcomposenoteapp.features_note.data.repository

import com.cleanarchcomposenoteapp.features_note.data.datasource.local.NoteDao
import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

internal class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
        override fun getNotes(): Flow<List<Note>> {
            return noteDao.getNotes()
        }

        override suspend fun getNoteById(id: Int): Note? {
            return noteDao.getNoteById(id)
        }

        override suspend fun insertNote(note: Note) {
            return noteDao.insertNote(note)
        }

        override suspend fun deleteNote(note: Note) {
            return noteDao.deleteNote(note)
        }
}