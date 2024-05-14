package com.example.bookforum.ui.screenParts.messageCardComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.ui.screenParts.FilledButtonWithIcon

@Composable
fun MessageButtons(
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        FilledButtonWithIcon(
            imageVector = Icons.Filled.Delete,
            onClick = onDeleteClick,
            iconButtonColors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error
            ),
            modifier = modifier
        )
        FilledButtonWithIcon(
            imageVector = Icons.Filled.Edit,
            onClick = onEditButtonClick,
            iconButtonColors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        )
    }
}
