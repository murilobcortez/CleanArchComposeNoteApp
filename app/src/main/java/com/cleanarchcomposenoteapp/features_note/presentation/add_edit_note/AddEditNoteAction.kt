package com.cleanarchcomposenoteapp.features_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteAction {
    data class EnteredTitle(val value: String): AddEditNoteAction()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteAction()
    data class EnteredContent(val value: String): AddEditNoteAction()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteAction()
    data class ChangeColor(val color: Int): AddEditNoteAction()
    object SaveNote: AddEditNoteAction()
}