package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MessageText(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge
    )
}