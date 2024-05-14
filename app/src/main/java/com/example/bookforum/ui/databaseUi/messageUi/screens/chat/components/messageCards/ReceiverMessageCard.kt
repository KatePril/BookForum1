package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.EditedMessage
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.MessageText
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.ReceiverMessageHeader
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.messageContent.ReplyBlock

@Composable
internal fun ReceiverMessageCard(
    onMessageClick: () -> Unit,
    user: User,
    message: Message,
    replyMessage: Message?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        MessageCard(
            onMessageClick = onMessageClick,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_large),
                topEnd = dimensionResource(R.dimen.padding_large),
                bottomEnd = dimensionResource(R.dimen.padding_large)
            ),
            modifier = modifier.weight(2f)
        ){
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
        Spacer(modifier = modifier.weight(1f))
    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ReceiverMessageCardPreview() {
//    BookForumTheme {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            ReceiverMessageCard(
//                onMessageClick = {},
//                user = User(0, "anyablue", "", ""),
//                message = Message(
//                    0, "Hi :)", "12:00 06.05.2024", 0, 0, 0, 0
//                ),
//                replyMessage = null
//            )
//        }
//
//    }
//}