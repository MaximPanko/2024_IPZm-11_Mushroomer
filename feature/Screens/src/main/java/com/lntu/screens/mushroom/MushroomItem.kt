package com.lntu.screens.mushroom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lntu.screens.hike.hike_details.HikeDetailsUiState

@Composable
fun MushroomItem(
    mushroom: HikeDetailsUiState.MushroomUiState,
    onMushroomClicked: (String) -> Unit,
    onMoreClicked: (HikeDetailsUiState.MushroomUiState) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onMushroomClicked(mushroom.id)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )  {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Mushroom"
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    mushroom.name ?: "NAME",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    mushroom.weight.toString() ?: "43.3",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(
                onClick = { onMoreClicked(mushroom) }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
        }
    }
}

@Composable
@Preview
fun MushroomItemPreview() {
    MushroomItem(
        mushroom = HikeDetailsUiState.MushroomUiState(
            id = "1",
            name = "name",
            description = "description",
            weight = 432.3
        ),
        onMushroomClicked = {},
        onMoreClicked = {}
    )
}