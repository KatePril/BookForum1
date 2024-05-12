package com.example.bookforum.ui.databaseUi.postsUI.screens.feed.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.screenParts.EmptyListMsg
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
internal fun PostsDisplayBody(
    userId: Int,
    onCommentsButtonClick: (Int) -> Unit,
    onEditButtonClick: (Int) -> Unit,
    postsList: List<Post>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
    ) {
        if (postsList.isEmpty()) {
            EmptyListMsg(
                msgId = R.string.no_posts_msg,
                modifier = modifier.padding(contentPadding)
            )
        } else {
            PostsList(
                userId = userId,
                onCommentsButtonClick = onCommentsButtonClick,
                onEditButtonClick = onEditButtonClick,
                postsList = postsList,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
private fun PostsList(
    userId: Int,
    onCommentsButtonClick: (Int) -> Unit,
    onEditButtonClick: (Int) -> Unit,
    postsList: List<Post>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = modifier
    ) {
        items(postsList) { post ->
            PostItem(
                userId = userId,
                onCommentsButtonClick = { onCommentsButtonClick(post.id) },
                onEditButtonClick = { onEditButtonClick(post.id) },
                post = post,
                modifier = modifier
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmptyPostsDisplayBodyPreview() {
    BookForumTheme {
//        PostsDisplayBody(postsList = listOf())
    }
}