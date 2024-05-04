package com.example.bookforum.ui.databaseUi.postsUI.screens.editPost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.postsUI.states.PostCreationUiState
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.EditPostViewModel
import com.example.bookforum.ui.screenParts.BackButton
import com.example.bookforum.ui.screenParts.DeleteButton
import kotlinx.coroutines.launch

@Composable
fun EditPostScreen(
    navigateBack: (Int) -> Unit,
    viewModel: EditPostViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    EditPostBody(
        userId = viewModel.userId,
        postUIState = viewModel.postUiState,
        navigateBack = navigateBack,
        onUpdateClick = {
            coroutineScope.launch {
                viewModel.updatePost()
            }
        },
        onDeleteClick = {
            coroutineScope.launch {
                viewModel.deletePost()
            }
            navigateBack(viewModel.userId)
        },
        onInputValueChange = viewModel::updateUiState
    )
}

@Composable
private fun EditPostBody(
    userId: Int,
    postUIState: PostCreationUiState,
    navigateBack: (Int) -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onInputValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        BackButton(
            onClick = { navigateBack(userId) }
        )
        EditPostForm(
            postDetails = postUIState.postDetails,
            onValueChange = onInputValueChange,
            modifier =  modifier
        )
        Button(
            onClick = onUpdateClick,
            enabled = postUIState.areInputsValid,
            shape = MaterialTheme.shapes.small,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.edit_post_action))
        }
        DeleteButton(
            buttonText = R.string.delete_post_action,
            onDeleteClick = onDeleteClick
        )
    }
}