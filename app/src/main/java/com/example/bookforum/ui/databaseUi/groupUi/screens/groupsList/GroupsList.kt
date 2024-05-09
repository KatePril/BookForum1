package com.example.bookforum.ui.databaseUi.groupUi.screens.groupsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.Group
import com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList.ChatItem

@Composable
internal fun GroupsList(
    onItemClick: (Int) -> Unit,
    groupsList: List<Group>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = dimensionResource(R.dimen.padding_large),
            bottom = dimensionResource(R.dimen.padding_large)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier
    ) {
        items(groupsList) { group ->
            ChatItem(
                chatTitle = group.title,
                onItemClick = { onItemClick(group.id) }
            )
        }
    }
}