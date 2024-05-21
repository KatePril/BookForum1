package com.example.bookforum.ui.databaseUi.likedPostsUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsListViewModel
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikedPostsScreen(
    onCommentsButtonClick: (Int, Int) -> Unit,
    onEditButtonClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToChatsList: (Int) -> Unit,
    navigateToPostCreation: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: LikedPostsListViewModel = viewModel(factory = ForumViewModelProvider.Factory)
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToPostCreation(viewModel.userId) }
            ) {
                Icon(
                    imageVector = Icons.Filled.DriveFileRenameOutline,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        LikedPostsBody(
            userId = viewModel.userId,
            onCommentsButtonClick = { onCommentsButtonClick(viewModel.userId, it) },
            onEditButtonClick = { onEditButtonClick(viewModel.userId, it) },
            postsList = viewModel.postsListUiState ?: listOf(),
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}