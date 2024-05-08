package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.data.entities.Message
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
internal fun CurrentUserRow(
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    message: Message,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.weight(0.5f))
        MessageButtons(
            onDeleteClick = onDeleteClick,
            onEditButtonClick = onEditButtonClick,
            modifier = modifier
        )
        CurrentUserMessageCard(
            message = message,
            modifier = modifier.weight(2f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrentUserMessageCardPreview() {
    BookForumTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CurrentUserRow(
                onEditButtonClick = {},
                onDeleteClick = {},
                message = Message(0, "Hi :)", "12:00 06.05.2024", 0, 0, 1)
            )
        }

    }
}