package com.example.bookforum.ui.databaseUi.postsUI.screens.editPost

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
internal fun EditPostForm(
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
            onValueChange = { onValueChange(postDetails.copy(title = it)) },
            labelText = R.string.edit_title_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.author,
            onValueChange = { onValueChange(postDetails.copy(author = it)) },
            labelText = R.string.edit_author_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.published,
            onValueChange = { onValueChange(postDetails.copy(published = it))},
            labelText = R.string.edit_published_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            modifier = modifier
        )
        FormInput(
            value = postDetails.review,
            onValueChange = { onValueChange(postDetails.copy(review = it)) },
            labelText = R.string.edit_review_input,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            singleLine = false,
            modifier = modifier
        )
    }
}