package com.example.bookforum.ui.databaseUi.commentUi.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel

@Composable
fun CommentsScreen(
    navigateToFeed: (Int) -> Unit,
    viewModel: CommentViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {

}