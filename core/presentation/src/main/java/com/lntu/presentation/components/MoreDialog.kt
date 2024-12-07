package com.lntu.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lntu.presentation.R

@Composable
fun MoreDialog(
    show: Boolean,
    onDismissRequest: () -> Unit = {},
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    if (show) {
        Dialog(onDismissRequest = onDismissRequest) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        Color.White,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onEditClicked(); onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF66BB6A)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.edit),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Button(
                    onClick = { onDeleteClicked(); onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE53935)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        fontSize = 18.sp,
                        color = Color.White
                    )
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

