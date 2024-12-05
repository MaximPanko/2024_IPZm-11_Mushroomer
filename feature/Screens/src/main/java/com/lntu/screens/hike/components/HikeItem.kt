package com.lntu.screens.hike.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.unit.dp
import com.lntu.screens.hike.HikesScreenUiState
import java.time.LocalDateTime
import java.time.Month

@Composable
fun HikeItem(
    hike: HikesScreenUiState.HikeUiState,
    onHikeClicked: (String) -> Unit,
    onMoreClicked: (HikesScreenUiState.HikeUiState) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onHikeClicked(hike.id)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon (
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Hike icon",
                modifier = Modifier.size(24.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = hike.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = hike.date.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(
                onClick = { onMoreClicked(hike) }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options"
                )
            }
        }
    }
}

@Composable
@Preview
internal fun HikeItemPreview() {
    HikeItem(
        hike = HikesScreenUiState.HikeUiState(
            id = "1",
            name = "Hike name",
            date = LocalDateTime.of(2024, Month.MARCH, 1, 14, 45)
    ),
    onHikeClicked = {},
    onMoreClicked = {}
    )
}