package com.cleanarchcomposenoteapp.features_note.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.unit.dp
import com.cleanarchcomposenoteapp.features_note.domain.util.NoteOrder
import com.cleanarchcomposenoteapp.features_note.domain.util.OrderType

private const val TITLE_TEXT = "Title"
private const val DATE_TEXT = "Date"
private const val COLOR_TEXT = "Color"
private const val ASCENDING_TEXT = "Ascending"
private const val DESCENDING_TEXT = "Descending"

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = TITLE_TEXT,
                selected = noteOrder is NoteOrder.Title,
                onSelect = {
                    onOrderChange(
                        NoteOrder.Title(
                            noteOrder.orderType
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = DATE_TEXT,
                selected = noteOrder is NoteOrder.Date,
                onSelect = {
                    onOrderChange(
                        NoteOrder.Date(
                            noteOrder.orderType
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = COLOR_TEXT,
                selected = noteOrder is NoteOrder.Color,
                onSelect = {
                    onOrderChange(
                        NoteOrder.Color(
                            noteOrder.orderType
                        )
                    )
                }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            DefaultRadioButton(
                text = ASCENDING_TEXT,
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(
                        noteOrder.copy(
                            OrderType.Ascending
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = DESCENDING_TEXT,
                selected = noteOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(
                        noteOrder.copy(
                            OrderType.Descending
                        )
                    )
                }
            )
        }
    }
}