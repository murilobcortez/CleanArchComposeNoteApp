package com.cleanarchcomposenoteapp.features_note.presentation.util

private const val NOTES_SCREEN_ROUTE_NAME = "notes_screen"
private const val ADD_EDIT_SCREEN_ROUTE_NAME = "add_edit_note_screen"

sealed class ScreenNavigator(val route: String) {
    object NotesScreen: ScreenNavigator(NOTES_SCREEN_ROUTE_NAME)
    object AddEditNoteScreen: ScreenNavigator(ADD_EDIT_SCREEN_ROUTE_NAME)
}