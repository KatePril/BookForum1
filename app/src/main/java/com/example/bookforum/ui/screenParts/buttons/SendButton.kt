package com.example.bookforum.ui.screenParts.buttons

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SendButton(
    onSendClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onSendClick,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small),
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}