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
import com.example.bookforum.ui.databaseUi.booksUI.states.BookDetails
import com.example.bookforum.ui.databaseUi.booksUI.viewModels.BookCreationViewModel

@Composable
fun BookCreationScreen(
    viewModel: BookCreationViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {

}

@Composable
fun BookCreationForum(
    bookDetails: BookDetails,
    onValueChange: (BookDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        BookCreationInput(
            value = bookDetails.title,
            onValueChange = {title: String -> onValueChange(bookDetails.copy(title = title))},
            labelText = R.string.title_input,
            modifier = modifier
        )
        BookCreationInput(
            value = bookDetails.author,
            onValueChange = {author: String -> onValueChange(bookDetails.copy(author = author))},
            labelText = R.string.authors_input,
            modifier = modifier
        )
        BookCreationInput(
            value = bookDetails.review,
            onValueChange = {review: String -> onValueChange(bookDetails.copy(review = review))},
            labelText = R.string.review_input,
            singleLine = false,
            modifier = modifier
        )
    }
}

@Composable
fun BookCreationInput(
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