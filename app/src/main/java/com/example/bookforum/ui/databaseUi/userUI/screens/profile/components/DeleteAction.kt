package com.example.bookforum.ui.databaseUi.userUI.screens.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R

@Composable
internal fun DeleteButton(
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isAlertDialogOpened by remember {  mutableStateOf(false) }

    Button(
        onClick = { isAlertDialogOpened = true },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ),
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.delete_account_action))
    }

    if (isAlertDialogOpened) {
        AlertDeleteDialog(
            onDismissClick = { isAlertDialogOpened = false },
            onConfirmClick = {
                onDeleteClick()
                isAlertDialogOpened = false
            }
        )
    }
}

@Composable
private fun AlertDeleteDialog(
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null
            )
        },
        title = {
            Text(text = stringResource(R.string.delete_alert_title))
        },
        text = {
            Text(text = stringResource(R.string.delete_alert_text))
        },
        onDismissRequest = onConfirmClick,
        confirmButton = {
            TextButton(
                onClick = onConfirmClick
            ) {
                Text(text = stringResource(R.string.confirm_delete_action))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissClick
            ) {
                Text(text = stringResource(R.string.dismiss_delete_action))
            }
        }
    )
}
