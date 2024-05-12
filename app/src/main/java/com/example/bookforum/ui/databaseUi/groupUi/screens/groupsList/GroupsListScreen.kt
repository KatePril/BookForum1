package com.example.bookforum.ui.databaseUi.groupUi.screens.groupsList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.Group
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupsListViewModel
import com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList.ChatsNavigationButtons
import com.example.bookforum.ui.screenParts.EmptyListMsg
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupsListScreen(
    onCreateGroupClick: () -> Unit,
    onItemClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToChatsList: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: GroupsListViewModel = viewModel(factory = ForumViewModelProvider.Factory)
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
        GroupsListBody(
            onCreateGroupClick = onCreateGroupClick,
            navigateToPrivateChats = { navigateToChatsList(viewModel.userId) }, 
            onItemClick = { onItemClick(viewModel.userId, it) }, 
            groupsList = viewModel.groupsList,
            contentPadding = innerPadding
        )
    }
}

@Composable
private fun GroupsListBody(
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