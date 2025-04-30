package com.cleanarchcomposenoteapp.features_note.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleanarchcomposenoteapp.features_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)

abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}