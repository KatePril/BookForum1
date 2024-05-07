package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.CurrentUserRow
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.ReceiverMessageCard
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails

@Composable
internal fun ChatBody(
    onDeleteClick: (Int) -> Unit,
    onEditButtonClick: (MessageDetails) -> Unit,
    receiver: User,
    messagesList: List<Message>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Log.i("MESSAGES", messagesList.toString())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
    ) {
        if (messagesList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_messages_msg),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            )
        } else {
            MessagesList(
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
internal fun MessagesList(
    onDeleteClick: (Int) -> Unit,
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
            if (message.senderId == receiver.id) {
                ReceiverMessageCard(
                    user = receiver,
                    message = message,
                    modifier = modifier
                )
            } else {
                CurrentUserRow(
                    onDeleteClick = { onDeleteClick(message.id) },
                    onEditButtonClick = { onEditButtonClick(message.toDetails()) },
                    message = message,
                    modifier =  modifier
                )
            }
        }
    }
}