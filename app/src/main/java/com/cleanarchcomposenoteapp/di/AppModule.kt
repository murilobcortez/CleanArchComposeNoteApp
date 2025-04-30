package com.cleanarchcomposenoteapp.di

import com.cleanarchcomposenoteapp.features_note.data.repository.NoteRepositoryImpl
import com.cleanarchcomposenoteapp.features_note.domain.repository.NoteRepository
import org.koin.dsl.module

val appModule = module {
    single<NoteRepository> { NoteRepositoryImpl(get()) }
}
