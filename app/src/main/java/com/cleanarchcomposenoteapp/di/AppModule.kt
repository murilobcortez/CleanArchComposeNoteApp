package com.cleanarchcomposenoteapp.di

import android.app.Application
import androidx.room.Room
import com.cleanarchcomposenoteapp.features_note.data.datasource.local.NoteDatabase
import com.cleanarchcomposenoteapp.features_note.data.repository.NoteRepositoryImpl
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository
import com.cleanarchcomposenoteapp.features_note.domain.usecase.AddNoteUseCase
import com.cleanarchcomposenoteapp.features_note.domain.usecase.DeleteNoteUseCase
import com.cleanarchcomposenoteapp.features_note.domain.usecase.GetNoteUseCase
import com.cleanarchcomposenoteapp.features_note.domain.usecase.GetNotesUseCase
import com.cleanarchcomposenoteapp.features_note.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDataBase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "table_notes"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository) : NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(noteRepository),
            deleteNoteUseCase = DeleteNoteUseCase(noteRepository),
            addNoteUseCase = AddNoteUseCase(noteRepository),
            getNoteUseCase = GetNoteUseCase(noteRepository),
        )
    }
}






