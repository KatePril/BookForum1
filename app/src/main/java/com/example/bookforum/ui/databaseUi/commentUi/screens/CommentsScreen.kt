package com.example.bookforum.ui.databaseUi.commentUi.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import com.example.bookforum.ui.databaseUi.postsUI.states.UserByIdUiState
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun CommentsScreen(
    navigateToFeed: (Int) -> Unit,
    viewModel: CommentViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Log.i("COMMENTS_LIST", viewModel.commentsUiState.toString())

    CommentScreenBody(
        navigateToFeed = { navigateToFeed(viewModel.userId) },
        commentCreationUiState = viewModel.commentCreationUiState,
        onValueChange = viewModel::updateUiState,
        onSendClick = {
            coroutineScope.launch {
                viewModel.saveComment()
            }
        },
        getUserById = viewModel::getUserById,
        commentsList = viewModel.commentsUiState,
    )
}

@Composable
fun CommentScreenBody(
    navigateToFeed: () -> Unit,
    commentCreationUiState: CommentCreationUiState,
    onValueChange: (CommentDetails) -> Unit,
    onSendClick: () -> Unit,
    getUserById: (Int) -> StateFlow<UserByIdUiState>,
    commentsList: List<Comment>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier.fillMaxWidth()
        ) {
            ButtonWithIcon(
                imageVector = Icons.Filled.ArrowBack,
                onClick = navigateToFeed
            )
        }
        CommentCreationForm(
            commentCreationUiState = commentCreationUiState,
            onValueChange = onValueChange,
            onSendClick = onSendClick
        )
        if (commentsList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_comments_msg),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(top = 16.dp)
            )
        } else {
            CommentsList(
                getUserById = getUserById,
                commentsList = commentsList,
            )
        }
    }
}

@Composable
private fun CommentsList(
    getUserById: (Int) -> StateFlow<UserByIdUiState>,
    commentsList: List<Comment>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(top = 16.dp)
    ) {
        items(commentsList) {comment ->
            val userUiState = getUserById(comment.userId).collectAsState()
            CommentCard(
                comment = comment,
                user = userUiState.value.user,
                modifier = modifier
            )
        }
    }
}