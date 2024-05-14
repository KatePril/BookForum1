package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.cardBlocks.MessageCard
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.cardBlocks.MessageCardColumn
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.CurrentUserMessageHeader
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.EditedMessage
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.MessageText
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.ReplyBlock

@Composable
internal fun CurrentUserMessageCard(
    onMessageClick: () -> Unit,
    message: Message,
    replyMessage: Message?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    MessageCard(
        onMessageClick = onMessageClick,
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_large),
            topEnd = dimensionResource(R.dimen.padding_large),
            bottomStart = dimensionResource(R.dimen.padding_large)
        ),
        modifier = modifier
    ){
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