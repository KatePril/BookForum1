package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.messageCards

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.MessageCardColumn
import com.example.bookforum.ui.screenParts.messageCardComponents.cardBlocks.ReceiverMsgCard
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.EditedMessage
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.MessageText
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.ReceiverMessageHeader
import com.example.bookforum.ui.screenParts.messageCardComponents.messageContent.ReplyBlock

@Composable
internal fun ReceiverGroupMessageCard(
    onMessageClick: () -> Unit,
    user: User,
    message: GroupMessage,
    replyMessage: GroupMessage?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    ReceiverMsgCard(
        onMessageClick = onMessageClick,
        modifier = modifier
    ) {
        MessageCardColumn {
            ReceiverMessageHeader(
                username = user.username,
                date = message.date
            )
            if (replyMessage != null && replySender != null) {
                ReplyBlock(
                    username = replySender.username,
                    text = replyMessage.text,
                    surfaceColor = MaterialTheme.colorScheme.secondary,
                    textColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            }
            MessageText(text = message.text)
            EditedMessage(edited = message.edited)
        }
    }
}