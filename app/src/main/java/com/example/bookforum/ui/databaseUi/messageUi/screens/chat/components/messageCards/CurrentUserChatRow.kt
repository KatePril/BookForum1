package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.CurrentUserRow

@Composable
internal fun CurrentUserChatRow(
    replyMessage: Message?,
    replySender: User?,
    onMessageClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    message: Message,
    modifier: Modifier = Modifier
) {
    CurrentUserRow(
        onDeleteClick = onDeleteClick,
        onEditButtonClick = onEditButtonClick,
        modifier = modifier
    ) {
        CurrentUserMessageCard(
            replySender = replySender,
            replyMessage = replyMessage,
            onMessageClick = onMessageClick,
            message = message,
            modifier = modifier.weight(2f)
        )
    }
}