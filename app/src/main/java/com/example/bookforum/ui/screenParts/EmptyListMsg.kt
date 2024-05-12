package com.example.bookforum.ui.screenParts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EmptyListMsg(
    @StringRes msgId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(msgId),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.displayMedium,
        modifier = modifier
    )
}