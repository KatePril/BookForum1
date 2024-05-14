package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.EditedMessage
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.MessageText
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.ReceiverMessageHeader
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents.ReplyBlock

@OptIn(ExperimentalMaterial3Api::class)
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
        Card(
            onClick = onMessageClick,
            modifier = modifier
                .weight(2f)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation)
            ),
            shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_large),
                topEnd = dimensionResource(R.dimen.padding_large),
                bottomEnd = dimensionResource(R.dimen.padding_large)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(
                modifier = modifier.padding(
                    top = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_large),
                    start = dimensionResource(R.dimen.padding_large),
                    end = dimensionResource(R.dimen.padding_large)
                )
            ) {
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