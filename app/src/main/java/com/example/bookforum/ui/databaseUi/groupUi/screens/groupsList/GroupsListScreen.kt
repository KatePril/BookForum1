package com.example.bookforum.ui.databaseUi.groupUi.screens.groupsList

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupsListViewModel
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupsListScreen(
    onCreateGroupClick: (Int) -> Unit,
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
            onCreateGroupClick = { onCreateGroupClick(viewModel.userId) },
            navigateToPrivateChats = { navigateToChatsList(viewModel.userId) }, 
            onItemClick = { onItemClick(viewModel.userId, it) }, 
            groupsList = viewModel.groupsList,
            contentPadding = innerPadding
        )
    }
}