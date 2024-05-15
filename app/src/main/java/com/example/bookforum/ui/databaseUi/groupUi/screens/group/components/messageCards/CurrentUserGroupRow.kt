package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.messageCards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.CurrentUserRow

@Composable
internal fun CurrentUserGroupRow(
    onMessageClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    message: GroupMessage,
    replyMessage: GroupMessage?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    CurrentUserRow(
        onDeleteClick = onDeleteClick,
        onEditButtonClick = onEditButtonClick,
        modifier = modifier
    ) {
        CurrentUserGroupMessageCard(
            replySender = replySender,
            replyMessage = replyMessage,
            onMessageClick = onMessageClick,
            message = message,
            modifier = modifier.weight(2f)
        )
    }
}