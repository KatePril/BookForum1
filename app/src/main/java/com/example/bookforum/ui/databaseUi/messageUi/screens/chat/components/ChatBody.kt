package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User

@Composable
internal fun ChatBody(
    receiver: User,
    messagesList: List<Message>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    if (messagesList.isEmpty()) {
        Text(
            text = stringResource(R.string.no_messages_msg),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large))
        )
    } else {
        MessagesList(
            receiver = receiver,
            messagesList = messagesList,
            contentPadding = contentPadding,
            modifier = modifier
        )
    }
}

@Composable
internal fun MessagesList(
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
            if (message.senderId == receiver.id) {
                ReceiverMessageCard(
                    user = receiver,
                    message = message,
                    modifier = modifier
                )
            } else {
                CurrentUserMessageCard(
                    message = message,
                    modifier =  modifier
                )
            }
        }
    }
}