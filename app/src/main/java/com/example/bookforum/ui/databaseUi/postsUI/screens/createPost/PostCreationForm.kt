package com.example.bookforum.ui.databaseUi.postsUI.screens.createPost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.postsUI.screens.componets.PostForm
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails

@Composable
internal fun PostCreationForm(
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    PostForm(
        titleLabelTextId = R.string.title_input,
        authorLabelTextId = R.string.authors_input,
        publishedLabelTextId = R.string.published_input,
        reviewLabelTextId = R.string.review_input,
        postDetails = postDetails,
        onValueChange = onValueChange,
        imeAction = ImeAction.Next,
        modifier = modifier
    )
}