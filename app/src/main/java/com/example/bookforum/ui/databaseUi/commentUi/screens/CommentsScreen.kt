package com.example.bookforum.ui.databaseUi.commentUi.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.commentUi.screens.components.CommentScreenBody
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import kotlinx.coroutines.launch

@Composable
fun CommentsScreen(
    navigateToFeed: (Int) -> Unit,
    viewModel: CommentViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    CommentScreenBody(
        navigateToFeed = { navigateToFeed(viewModel.userId) },
        commentCreationUiState = viewModel.commentCreationUiState,
        onValueChange = viewModel::updateUiState,
        onSendClick = {
            coroutineScope.launch {
                viewModel.saveComment()
            }
        },
        commentsList = viewModel.commentsUiState,
        users = viewModel.usersMap
    )
}

