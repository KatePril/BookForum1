package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R

@Composable
fun MessageHeader(
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}

@Composable
fun ReceiverMessageHeader(
    username: String,
    date: String,
    modifier: Modifier = Modifier
) {
    MessageHeader(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ){
        Text(
            text = username,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun CurrentUserMessageHeader(
    date: String,
    modifier: Modifier = Modifier
) {
    MessageHeader(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
