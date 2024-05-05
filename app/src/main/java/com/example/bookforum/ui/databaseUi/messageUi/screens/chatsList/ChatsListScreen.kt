package com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatsListViewModel
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatsListScreen(
    onItemClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToChatsList: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: ChatsListViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            ForumTopAppBar(
                quitAccount = quitAccount,
                navigateToGlobalPage = { navigateToGlobalPage(viewModel.userId) },
                navigateToChatsList = { navigateToChatsList(viewModel.userId) },
                navigateToFavouritePosts = { navigateToFavouritePosts(viewModel.userId) },
                navigateToProfile = { navigateToProfile(viewModel.userId) }
            )
        }
    ) {
        ChatsListBody(
            onItemClick = { onItemClick(viewModel.userId, it) },
            usersList = viewModel.contactsList
        )
    }
}

@Composable
private fun ChatsListBody(
    onItemClick: (Int) -> Unit,
    usersList: List<User>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
    ) {
        if (usersList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_chats_msg),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.padding(contentPadding)
            )
        } else {
            ChatsList(
                onItemClick = onItemClick,
                usersList = usersList,
                contentPadding = contentPadding,
                modifier = modifier
            )
        }
    }
}