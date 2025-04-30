package com.cleanarchcomposenoteapp.features_note.presentation

import android.app.Application
import com.cleanarchcomposenoteapp.di.appModule
import com.cleanarchcomposenoteapp.di.dataBaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CleanArchComposeNoteAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CleanArchComposeNoteAppApplication)
            modules(appModule, dataBaseModule)
        }
    }
}