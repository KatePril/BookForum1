package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.GroupMessage
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails
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