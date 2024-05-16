package com.example.bookforum.ui.databaseUi.groupUi.screens.groupsList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.Group
import com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList.ChatsNavigationButtons
import com.example.bookforum.ui.screenParts.EmptyListMsg

@Composable
internal fun GroupsListBody(
    onCreateGroupClick: () -> Unit,
    navigateToPrivateChats: () -> Unit,
    onItemClick: (Int) -> Unit,
    groupsList: List<Group>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
        ) {
            ChatsNavigationButtons(
                navigateToPrivateChats = navigateToPrivateChats,
                enabledGroups = false,
                modifier = modifier
            )
            Button(
                onClick = onCreateGroupClick,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.create_group_action))
            }
            if (groupsList.isEmpty()) {
                EmptyListMsg(
                    msgId = R.string.no_groups_msg,
                    modifier = modifier
                )
            } else {
                GroupsList(
                    onItemClick = onItemClick,
                    groupsList = groupsList,
                    modifier = modifier
                )
            }
        }
    }
}