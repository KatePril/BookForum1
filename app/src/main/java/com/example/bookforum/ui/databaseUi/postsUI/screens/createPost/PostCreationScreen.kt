package com.example.bookforum.ui.databaseUi.postsUI.screens.createPost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.postsUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.PostCreationViewModel
import com.example.bookforum.ui.screenParts.FormInput
import kotlinx.coroutines.launch

@Composable
fun PostCreationScreen(
    navigateToPostsDisplay: (Int) -> Unit,
    viewModel: PostCreationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val userUiState = viewModel.userUiState.collectAsState()

    PostCreationBody(
        postCreationUiState = viewModel.postCreationUiState,
        onValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.savePost()
            }
            navigateToPostsDisplay(userUiState.value.user.id)
        },
        onCancelClick = {
            navigateToPostsDisplay(userUiState.value.user.id)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun PostCreationBody(
    postCreationUiState: PostCreationUiState,
    onValueChange: (PostDetails) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        PostCreationForm(
            postDetails = postCreationUiState.postDetails,
            onValueChange = onValueChange,
            modifier = modifier
        )
        if (!postCreationUiState.areInputsValid) {
            Text(
                text = stringResource(R.string.inputs_empty_msg),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = onSaveClick,
            enabled = postCreationUiState.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.submit_post_action))
        }
        Button(
            onClick = onCancelClick,
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.cancel_action))
        }
    }
}

@Composable
private fun PostCreationForm(
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        FormInput(
            value = postDetails.title,
            onValueChange = {title: String -> onValueChange(postDetails.copy(title = title))},
            labelText = R.string.title_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.author,
            onValueChange = {author: String -> onValueChange(postDetails.copy(author = author))},
            labelText = R.string.authors_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.published,
            onValueChange = {published: String -> onValueChange(postDetails.copy(published = published))},
            labelText = R.string.published_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.review,
            onValueChange = {review: String -> onValueChange(postDetails.copy(review = review))},
            labelText = R.string.review_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            singleLine = false,
            modifier = modifier
        )
    }
}