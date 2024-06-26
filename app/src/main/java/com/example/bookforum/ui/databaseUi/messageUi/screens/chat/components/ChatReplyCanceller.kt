package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.messageCardComponents.ReplyCanceller

@Composable
internal fun ChatReplyCanceller(
    getReplyById: (Int) -> Message?,
    getReplySender: (Int) -> User?,
    updateUiState: (MessageDetails) -> Unit,
    messageDetails: MessageDetails,
    modifier: Modifier = Modifier
) {
    val reply = getReplyById(messageDetails.reply)

    if (reply != null) {
        val sender = getReplySender(reply.senderId)

        ReplyCanceller(
            username = sender?.username ?: "",
            text = reply.text,
            onCancelClick = {
                updateUiState(messageDetails.copy(reply = 0))
            },
            modifier = modifier
        )
    }
}