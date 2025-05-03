package com.cleanarchcomposenoteapp.features_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleanarchcomposenoteapp.features_note.domain.model.Note
import com.cleanarchcomposenoteapp.features_note.domain.usecase.NoteUseCases
import com.cleanarchcomposenoteapp.features_note.domain.util.NoteOrder
import com.cleanarchcomposenoteapp.features_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote : Note? = null
    private var getNotesJob : Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onAction(action: NotesAction){
        when(action) {
            is NotesAction.Order -> {
                if(state.value.noteOrder::class == action.noteOrder::class &&
                    state.value.noteOrder.orderType == action.noteOrder.orderType){
                    return
                }
                getNotes(action.noteOrder)
            }
            is NotesAction.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNoteUseCase(action.note)
                    recentlyDeletedNote = action.note
                }
            }
            is NotesAction.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesAction.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}