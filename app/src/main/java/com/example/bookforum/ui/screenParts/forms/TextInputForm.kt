package com.example.bookforum.ui.screenParts.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.ui.screenParts.buttons.SendButton

@Composable
fun TextInputForm(
    enabled: Boolean,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .padding(end = dimensionResource(R.dimen.padding_large))
                .weight(2f),
            content = content
        )
        Column (
            modifier = modifier
        ) {
            SendButton(
                onSendClick = onSendClick,
                enabled = enabled,
                modifier = modifier
            )
        }
    }
}