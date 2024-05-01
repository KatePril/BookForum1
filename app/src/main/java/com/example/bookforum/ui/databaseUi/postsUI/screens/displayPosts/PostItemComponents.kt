package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import com.example.bookforum.ui.screenParts.ExpandButton

@Composable
internal fun PostButtons(
    expanded: Boolean,
    liked: Boolean,
    onLikeButtonClick: () -> Unit,
    onCommentsButtonClick: () -> Unit,
    onExpandButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        ButtonWithIcon(
            imageVector = if (liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            onClick = onLikeButtonClick,
            modifier = modifier
        )
        ButtonWithIcon(
            imageVector = Icons.Filled.Comment,
            onClick = onCommentsButtonClick,
            modifier = modifier
        )
        ExpandButton(
            expanded = expanded,
            onClick = onExpandButtonClick,
            modifier = modifier
        )
    }
}

@Composable
internal fun PostInfo(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "By ${post.author}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Published: ${post.published}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}