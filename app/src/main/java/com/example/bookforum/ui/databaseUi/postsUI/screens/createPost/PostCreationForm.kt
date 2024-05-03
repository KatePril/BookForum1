package com.example.bookforum.ui.databaseUi.postsUI.screens.createPost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails
import com.example.bookforum.ui.screenParts.FormInput

@Composable
internal fun PostCreationForm(
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        FormInput(
            value = postDetails.title,
            onValueChange = {title: String -> onValueChange(postDetails.copy(title = title))},
            labelText = R.string.title_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = ImeAction.Next,
            modifier = modifier
        )
        FormInput(
            value = postDetails.author,
            onValueChange = {author: String -> onValueChange(postDetails.copy(author = author))},
            labelText = R.string.authors_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = ImeAction.Next,
            modifier = modifier
        )
        FormInput(
            value = postDetails.published,
            onValueChange = {published: String -> onValueChange(postDetails.copy(published = published))},
            labelText = R.string.published_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = ImeAction.Next,
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