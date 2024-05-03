package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.Post
import com.example.compose.BookForumTheme

@Composable
internal fun PostsDisplayBody(
    userId: Int,
    onCommentsButtonClick: (Int) -> Unit,
    postsList: List<Post>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(R.dimen.padding_large)),
    ) {
        if (postsList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_posts_msg),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.padding(contentPadding)
            )
        } else {
            PostsList(
                userId = userId,
                onCommentsButtonClick = onCommentsButtonClick,
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