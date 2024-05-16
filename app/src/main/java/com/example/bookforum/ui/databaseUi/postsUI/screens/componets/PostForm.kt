package com.example.bookforum.ui.databaseUi.postsUI.screens.componets

import androidx.annotation.StringRes
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
import com.example.bookforum.ui.screenParts.forms.FormInput

@Composable
internal fun PostForm(
    @StringRes titleLabelTextId: Int,
    @StringRes authorLabelTextId: Int,
    @StringRes publishedLabelTextId: Int,
    @StringRes reviewLabelTextId: Int,
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large))
    ) {
        FormInput(
            value = postDetails.title,
            onValueChange = { onValueChange(postDetails.copy(title = it)) },
            labelText = titleLabelTextId,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = imeAction,
            modifier = modifier
        )
        FormInput(
            value = postDetails.author,
            onValueChange = { onValueChange(postDetails.copy(author = it)) },
            labelText = authorLabelTextId,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = imeAction,
            modifier = modifier
        )
        FormInput(
            value = postDetails.published,
            onValueChange = { onValueChange(postDetails.copy(published = it))},
            labelText = publishedLabelTextId,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            imeAction = imeAction,
            modifier = modifier
        )
        FormInput(
            value = postDetails.review,
            onValueChange = { onValueChange(postDetails.copy(review = it)) },
            labelText = reviewLabelTextId,
            color = MaterialTheme.colorScheme.tertiaryContainer,
            singleLine = false,
            modifier = modifier
        )
    }
}