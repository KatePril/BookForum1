package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.messageCards

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.CurrentUserMsgCard
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.MessageCardColumn
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.CurrentUserMessageHeader
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.EditedMessage
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.MessageText
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.ReplyBlock

@Composable
internal fun CurrentUserGroupMessageCard(
    onMessageClick: () -> Unit,
    message: GroupMessage,
    replyMessage: GroupMessage?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    CurrentUserMsgCard(
        onMessageClick = onMessageClick,
        modifier = modifier
    ) {
        MessageCardColumn {
            CurrentUserMessageHeader(date = message.date)
            if (replyMessage != null && replySender != null) {
                ReplyBlock(
                    username = replySender.username,
                    text = replyMessage.text,
                    surfaceColor = MaterialTheme.colorScheme.tertiaryContainer,
                    textColor = MaterialTheme.colorScheme.secondary
                )
            }
            MessageText(text = message.text)
            EditedMessage(edited = message.edited)
        }
    }
}