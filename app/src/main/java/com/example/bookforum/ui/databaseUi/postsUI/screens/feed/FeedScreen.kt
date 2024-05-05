package com.example.bookforum.ui.databaseUi.postsUI.screens.feed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.postsUI.screens.feed.components.PostsDisplayBody
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.FeedViewModel
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen(
    onCommentsButtonClick: (Int, Int) -> Unit,
    onEditButtonClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToChatsList: (Int) -> Unit,
    navigateToPostCreation: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    feedViewModel: FeedViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val postsUiState by feedViewModel.postsUiState.collectAsState()

    Scaffold(
        topBar = {
            ForumTopAppBar(
                quitAccount = quitAccount,
                navigateToGlobalPage = { navigateToGlobalPage(feedViewModel.userId) },
                navigateToChatsList = { navigateToChatsList(feedViewModel.userId) },
                navigateToFavouritePosts = { navigateToFavouritePosts(feedViewModel.userId) },
                navigateToProfile = { navigateToProfile(feedViewModel.userId) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToPostCreation(feedViewModel.userId) }
            ) {
                Icon(
                    imageVector = Icons.Filled.DriveFileRenameOutline,
                    contentDescription = null
                )
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        PostsDisplayBody(
            userId = feedViewModel.userId,
            onCommentsButtonClick = { onCommentsButtonClick(feedViewModel.userId, it) },
            onEditButtonClick = { onEditButtonClick(feedViewModel.userId, it) },
            postsList = postsUiState.postsList,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}