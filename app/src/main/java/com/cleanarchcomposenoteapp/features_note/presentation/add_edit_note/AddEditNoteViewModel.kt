package com.cleanarchcomposenoteapp.features_note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchcomposenoteapp.features_note.domain.model.InvalidNoteException
import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val NOTE_TITLE_HINT = "Enter title"
private const val NOTE_CONTENT_HINT = "Enter some content"
private const val ADD_NOTE_EXCEPTION_MESSAGE = "Couldn't save note"

@HiltViewModel
internal class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = NOTE_TITLE_HINT
    ))
    val noteTitle : State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = NOTE_CONTENT_HINT
    ))
    val noteContent : State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableIntStateOf(Note.noteColors.random().toArgb())
    val noteColor : State<Int> = _noteColor

    private val _actionFlow = MutableSharedFlow<UiAction>()
    val actionFlow = _actionFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1){
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title.toString(),
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content.toString(),
                            isHintVisible = false
                        )
                        _noteColor.intValue = note.color
                    }
                }
            }
        }
    }

    fun onAction(action: AddEditNoteAction){
        when(action){
            is AddEditNoteAction.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = action.value
                )
            }
            is AddEditNoteAction.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !action.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteAction.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = action.value
                )
            }
            is AddEditNoteAction.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !action.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteAction.ChangeColor -> {
                _noteColor.intValue = action.color
            }
            is AddEditNoteAction.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = noteColor.value,
                                id = currentNoteId
                            )
                        )
                        _actionFlow.emit(UiAction.SaveNote)
                    }
                    catch (exception: InvalidNoteException){
                        _actionFlow.emit(
                            UiAction.ShowSnackbar(
                                message = exception.message ?: ADD_NOTE_EXCEPTION_MESSAGE
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiAction {
        data class ShowSnackbar(val message: String) : UiAction()
        object SaveNote : UiAction()
    }
}