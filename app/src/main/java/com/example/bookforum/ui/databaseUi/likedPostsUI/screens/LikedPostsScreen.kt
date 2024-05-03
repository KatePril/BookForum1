package com.example.bookforum.ui.databaseUi.likedPostsUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsListViewModel
import com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.components.PostItem
import com.example.bookforum.ui.screenParts.ForumTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LikedPostsScreen(
    onCommentsButtonClick: (Int, Int) -> Unit,
    quitAccount: () -> Unit,
    navigateToGlobalPage: (Int) -> Unit,
    navigateToPostCreation: (Int) -> Unit,
    navigateToFavouritePosts: (Int) -> Unit,
    navigateToProfile: (Int) -> Unit,
    viewModel: LikedPostsListViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val postsListUiState = viewModel.postsListUiState

    Scaffold(
        topBar = {
            ForumTopAppBar(
                quitAccount = quitAccount,
                navigateToGlobalPage = { navigateToGlobalPage(viewModel.userId) },
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
            postsList = postsListUiState ?: listOf(),
            contentPadding = innerPadding,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
internal fun LikedPostsBody(
    userId: Int,
    onCommentsButtonClick: (Int) -> Unit,
    postsList: List<Post>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
    ) {
        if (postsList.isEmpty()) {
            Text(
                text = "You haven't liked any posts yet",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.padding(contentPadding)
            )
        } else {
            LikedPostsList(
                userId = userId,
                onCommentsButtonClick = onCommentsButtonClick,
                postsList = postsList,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun LikedPostsList(
    userId: Int,
    onCommentsButtonClick: (Int) -> Unit,
    postsList: List<Post>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier
    ) {
        items(postsList) { post ->
            PostItem(
                userId = userId,
                onCommentsButtonClick = { onCommentsButtonClick(post.id) },
                post = post,
                modifier = modifier
            )
        }
    }
}
