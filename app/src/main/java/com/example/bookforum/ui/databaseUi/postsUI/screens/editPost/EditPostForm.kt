package com.example.bookforum.ui.databaseUi.postsUI.screens.editPost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.postsUI.screens.componets.PostForm
import com.example.bookforum.ui.databaseUi.postsUI.states.PostDetails

@Composable
internal fun EditPostForm(
    postDetails: PostDetails,
    onValueChange: (PostDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    PostForm(
        titleLabelTextId = R.string.edit_title_input,
        authorLabelTextId = R.string.edit_author_input,
        publishedLabelTextId = R.string.edit_published_input,
        reviewLabelTextId = R.string.edit_review_input,
        postDetails = postDetails,
        onValueChange = onValueChange,
        modifier = modifier
    )
}