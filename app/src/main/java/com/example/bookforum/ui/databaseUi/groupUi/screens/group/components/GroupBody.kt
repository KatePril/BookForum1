package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components

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
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.data.entities.toDetails
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.messageCards.CurrentUserGroupRow
import com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.messageCards.ReceiverGroupMessageCard
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails
import com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.ChatBody
import com.example.bookforum.ui.screenParts.EmptyListMsg

@Composable
internal fun GroupBody(
    currentUserId: Int,
    getMessageById: (Int) -> GroupMessage?,
    getUserById: (Int) -> User?,
    onMessageClick: (Int) -> Unit,
    onDeleteClick: (GroupMessage) -> Unit,
    onEditClick: (GroupMessageDetails) -> Unit,
    messagesList: List<GroupMessage>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .fillMaxHeight(),
    ) {
        if (messagesList.isEmpty()) {
            EmptyListMsg(
                msgId = R.string.no_messages_msg,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
            )
        } else {
            GroupMessagesList(
                currentUserId = currentUserId,
                getMessageById = getMessageById,
                getUserById = getUserById,
                onMessageClick = onMessageClick,
                onDeleteClick = onDeleteClick,
                onEditClick = onEditClick,
                messagesList = messagesList,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun GroupMessagesList(
    currentUserId: Int,
    getMessageById: (Int) -> GroupMessage?,
    getUserById: (Int) -> User?,
    onMessageClick: (Int) -> Unit,
    onDeleteClick: (GroupMessage) -> Unit,
    onEditClick: (GroupMessageDetails) -> Unit,
    messagesList: List<GroupMessage>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(messagesList) { groupMessage ->
            val reply = getMessageById(groupMessage.reply)
            if (currentUserId == groupMessage.senderId) {
                CurrentUserGroupRow(
                    onMessageClick = { onMessageClick(groupMessage.id) },
                    onDeleteClick = { onDeleteClick(groupMessage) },
                    onEditButtonClick = { onEditClick(groupMessage.toDetails()) },
                    message = groupMessage,
                    replyMessage = reply,
                    replySender = reply?.let { getUserById(it.senderId) }
                )
            } else {
                ReceiverGroupMessageCard(
                    onMessageClick = { onMessageClick(groupMessage.id) },
                    user = getUserById(groupMessage.senderId) ?: User(0, "", "", ""),
                    message = groupMessage,
                    replyMessage = reply,
                    replySender = reply?.let { getUserById(it.senderId) }
                )
            }
        }
    }
}