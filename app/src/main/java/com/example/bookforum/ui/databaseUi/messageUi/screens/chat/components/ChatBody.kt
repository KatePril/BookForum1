package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.CurrentUserChatRow
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.ReceiverMessageCard
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.EmptyListMsg

@Composable
internal fun ChatBody(
    getMessageById: (Int) -> Message?,
    getSenderById: (Int) -> User?,
    onMessageClick: (Int) -> Unit,
    onDeleteClick: (Message) -> Unit,
    onEditButtonClick: (MessageDetails) -> Unit,
    receiver: User,
    messagesList: List<Message>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)).fillMaxHeight(),
    ) {
        if (messagesList.isEmpty()) {
            EmptyListMsg(
                msgId = R.string.no_messages_msg,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            )
        } else {
            MessagesList(
                getMessageById =  getMessageById,
                getSenderById = getSenderById,
                onMessageClick = onMessageClick,
                onDeleteClick = onDeleteClick,
                onEditButtonClick = onEditButtonClick,
                receiver = receiver,
                messagesList = messagesList,
                contentPadding = contentPadding,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun MessagesList(
    getSenderById: (Int) -> User?,
    getMessageById: (Int) -> Message?,
    onMessageClick: (Int) -> Unit,
    onDeleteClick: (Message) -> Unit,
    onEditButtonClick: (MessageDetails) -> Unit,
    receiver: User,
    messagesList: List<Message>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(messagesList) {message ->
            val reply = getMessageById(message.reply)
            if (message.senderId == receiver.id) {
                ReceiverMessageCard(
                    onMessageClick = { onMessageClick(message.id) },
                    user = receiver,
                    message = message,
                    modifier = modifier,
                    replyMessage = reply,
                    replySender = reply?.let { getSenderById(it.senderId) }
                )
            } else {
                CurrentUserChatRow(
                    onMessageClick = { onMessageClick(message.id) },
                    onDeleteClick = { onDeleteClick(message) },
                    onEditButtonClick = { onEditButtonClick(message.toDetails()) },
                    message = message,
                    modifier =  modifier,
                    replyMessage = reply,
                    replySender = reply?.let { getSenderById(it.senderId) }
                )
            }
        }
    }
}