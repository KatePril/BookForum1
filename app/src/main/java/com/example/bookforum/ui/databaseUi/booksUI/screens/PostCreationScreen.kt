package com.example.bookforum.ui.databaseUi.booksUI.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.R
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.booksUI.states.PostDetails
import com.example.bookforum.ui.databaseUi.booksUI.viewModels.PostCreationViewModel

@Composable
fun PostCreationScreen(
    viewModel: PostCreationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {

}

@Composable
fun PostCreationForum(
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        PostCreationInput(
            value = postDetails.title,
            onValueChange = {title: String -> onValueChange(postDetails.copy(title = title))},
            labelText = R.string.title_input,
            modifier = modifier
        )
        PostCreationInput(
            value = postDetails.author,
            onValueChange = {author: String -> onValueChange(postDetails.copy(author = author))},
            labelText = R.string.authors_input,
            modifier = modifier
        )
        PostCreationInput(
            value = postDetails.review,
            onValueChange = {review: String -> onValueChange(postDetails.copy(review = review))},
            labelText = R.string.review_input,
            singleLine = false,
            modifier = modifier
        )
    }
}

@Composable
fun PostCreationInput(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes labelText: Int,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = labelText)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
        ),
        modifier = modifier.fillMaxWidth(),
        singleLine = singleLine
    )
}