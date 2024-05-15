package com.example.bookforum.ui.screenParts.messageCardComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon

@Composable
fun ReplyCanceller(
    username: String,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier
            .fillMaxWidth()
            .absolutePadding(top = 745.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(text = "$username: $text")
            ButtonWithIcon(
                imageVector = Icons.Filled.Cancel,
                onClick = onClick,
                tint = MaterialTheme.colorScheme.error,
                modifier = modifier.size(16.dp)
            )
        }
    }
}