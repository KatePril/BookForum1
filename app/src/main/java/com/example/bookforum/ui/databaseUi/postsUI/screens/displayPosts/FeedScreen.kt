package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.data.entities.LikedPost
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.FeedViewModel
import com.example.bookforum.ui.screenParts.ForumTopAppBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen(
    onCommentsButtonClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToPostCreation: (Int) -> Unit,
    navigateToFavouritePosts: () -> Unit,
    navigateToProfile: (Int) -> Unit,
    feedViewModel: FeedViewModel = viewModel(factory = ForumViewModelProvider.Factory),
    likedPostsViewModel: LikedPostsViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val postsUiState by feedViewModel.postsUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ForumTopAppBar(
                quitAccount = quitAccount,
                navigateToGlobalPage = { navigateToGlobalPage(feedViewModel.userId) },
                navigateToFavouritePosts = navigateToFavouritePosts,
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
            checkLikedPostExistence = {
                likedPostsViewModel.checkLikedPostExistence(feedViewModel.userId, it)
            },
            onLikeButtonClicked = { id: Int, postId: Int ->
                coroutineScope.launch {
                    likedPostsViewModel
                        .updateLikedPost(LikedPost(
                            id = id,
                            userId = feedViewModel.userId,
                            postId = postId
                        )
                    )
                }
            },
            onCommentsButtonClick = { onCommentsButtonClick(feedViewModel.userId, it) },
            postsList = postsUiState.postsList,
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}