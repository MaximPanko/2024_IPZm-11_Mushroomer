package com.lntu.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
fun MoreDialog(
    show: Boolean,
    onDismissRequest: () -> Unit = {},
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card {
                Button(
                    onClick = { onEditClicked(); onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { onDeleteClicked(); onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
@Preview
fun MoreDialogPreview() {
    MoreDialog(
        show = true,
        onEditClicked = {},
        onDeleteClicked = {}
    )
}

