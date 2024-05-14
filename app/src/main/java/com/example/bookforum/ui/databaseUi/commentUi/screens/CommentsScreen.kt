package com.example.bookforum.ui.databaseUi.commentUi.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.commentUi.screens.components.CommentCard
import com.example.bookforum.ui.databaseUi.commentUi.screens.components.CommentCreationForm
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon
import com.example.bookforum.ui.screenParts.EmptyListMsg
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
        users = viewModel.usersHashMap
    )
}

@Composable
fun CommentScreenBody(
    navigateToFeed: () -> Unit,
    commentCreationUiState: CommentCreationUiState,
    onValueChange: (CommentDetails) -> Unit,
    onSendClick: () -> Unit,
    commentsList: List<Comment>,
    users: Map<Int, User>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
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
            EmptyListMsg(
                msgId = R.string.no_comments_msg,
                modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large))
            )
        } else {
            CommentsList(
                commentsList = commentsList,
                users = users
            )
        }
    }
}

@Composable
private fun CommentsList(
    commentsList: List<Comment>,
    users: Map<Int, User>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_large))
    ) {
        items(commentsList) {comment ->
            users[comment.userId]?.let {
                CommentCard(
                    comment = comment,
                    user = it,
                    modifier = modifier
                )
            }
        }
    }
}