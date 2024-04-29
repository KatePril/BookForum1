package com.example.bookforum.ui.databaseUi.commentUi.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentCreationUiState
import com.example.bookforum.ui.databaseUi.commentUi.states.CommentDetails
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import com.example.bookforum.ui.screenParts.FormInputWithMessage
import com.example.compose.BookForumTheme
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
        }
    )
}

@Composable
fun CommentScreenBody(
    navigateToFeed: () -> Unit,
    commentCreationUiState: CommentCreationUiState,
    onValueChange: (CommentDetails) -> Unit,
    onSendClick: () -> Unit,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Start
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
    }
}