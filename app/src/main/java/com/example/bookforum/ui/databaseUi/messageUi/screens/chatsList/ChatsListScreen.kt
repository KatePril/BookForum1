package com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatsListViewModel
import com.example.bookforum.ui.screenParts.EmptyListMsg
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatsListScreen(
    navigateToGroups: (Int) -> Unit,
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
    ) { innerPadding ->
        ChatsListBody(
            navigateToGroups = { navigateToGroups(viewModel.userId) },
            onItemClick = { onItemClick(viewModel.userId, it) },
            usersList = viewModel.contactsList,
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun ChatsListBody(
    navigateToGroups: () -> Unit,
    onItemClick: (Int) -> Unit,
    usersList: List<User>,
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
                navigateToGroups = navigateToGroups,
                enabledPrivateChats = false,
                modifier = modifier
            )
            if (usersList.isEmpty()) {
                EmptyListMsg(
                    msgId = R.string.no_chats_msg,
                    modifier = modifier
                )
            } else {
                ChatsList(
                    onItemClick = onItemClick,
                    usersList = usersList,
                    modifier = modifier
                )
            }
        }
    }

}