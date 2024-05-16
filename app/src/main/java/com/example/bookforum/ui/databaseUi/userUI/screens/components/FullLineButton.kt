package com.example.bookforum.ui.databaseUi.userUI.screens.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun FullLineButton(
    @StringRes msgTextId: Int,
    @StringRes buttonTextId: Int,
    isMsgShown: Boolean,
    onClick: ()-> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    if (isMsgShown) {
        Text(
            text = stringResource(msgTextId),
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(stringResource(buttonTextId))
    }
}