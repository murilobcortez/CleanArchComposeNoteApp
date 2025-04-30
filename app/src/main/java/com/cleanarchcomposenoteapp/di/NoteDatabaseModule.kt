package com.cleanarchcomposenoteapp.di

import android.app.Application
import androidx.room.Room
import com.cleanarchcomposenoteapp.features_note.data.datasource.local.NoteDao
import com.cleanarchcomposenoteapp.features_note.data.datasource.local.NoteDatabase
import org.koin.dsl.module

fun provideDataBase(application: Application): NoteDatabase =
    Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        "table_notes"
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao

val dataBaseModule = module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}