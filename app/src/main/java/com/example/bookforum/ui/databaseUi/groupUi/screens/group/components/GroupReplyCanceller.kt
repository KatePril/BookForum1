package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails
import com.example.bookforum.ui.screenParts.messageCardComponents.ReplyCanceller

@Composable
internal fun GroupReplyCanceller(
    getReplyById: (Int) -> GroupMessage?,
    getReplySender: (Int) -> User?,
    updateUiState: (GroupMessageDetails) -> Unit,
    groupMessageDetails: GroupMessageDetails,
    modifier: Modifier = Modifier
) {
    val reply = getReplyById(groupMessageDetails.reply)
    if (reply != null) {
        val sender = getReplySender(reply.senderId)
        ReplyCanceller(
            username = sender?.username ?: "",
            text = reply.text,
            onCancelClick = {
                updateUiState(groupMessageDetails.copy(reply = 0))
            },
            modifier = modifier
        )
    }
}