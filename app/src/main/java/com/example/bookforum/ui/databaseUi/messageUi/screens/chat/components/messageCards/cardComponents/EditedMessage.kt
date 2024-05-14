package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R

@Composable
internal fun EditedMessage(
    edited: Int,
    modifier: Modifier = Modifier
) {
    if (edited == 1) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.edited),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}