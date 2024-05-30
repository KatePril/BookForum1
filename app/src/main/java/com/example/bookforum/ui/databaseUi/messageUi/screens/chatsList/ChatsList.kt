package com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
internal fun ChatsList(
    onItemClick: (Int) -> Unit,
    usersList: List<User>,
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
        items(usersList) { user ->
            ChatItem(
                chatTitle = user.username,
                onItemClick = { onItemClick(user.id) },
                modifier = modifier
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatItem(
    chatTitle: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onItemClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
        )
    ) {
        Text(
            text = chatTitle,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatItemPreview() {
    BookForumTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ChatItem("anyablue", onItemClick = {})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatsListPreview() {
    BookForumTheme {
        ChatsList(
            onItemClick = {},
            usersList = listOf(
                User(1, "anyablue", "", "", ""),
                User(2, "Kate", "", "", ""),
                User(3, "Hermione", "", "", "")
            )
        )
    }
}