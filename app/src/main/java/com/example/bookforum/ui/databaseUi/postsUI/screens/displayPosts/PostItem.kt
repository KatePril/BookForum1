package com.example.bookforum.ui.databaseUi.postsUI.screens.displayPosts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.data.entities.Post
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import com.example.bookforum.ui.screenParts.ExpandButton
import com.example.compose.BookForumTheme

@Composable
internal fun PostItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var liked by remember { mutableStateOf(false) }
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Row(
                modifier = modifier
            ) {
                PostInfo(
                    post = post,
                    modifier = modifier.weight(2f)
                )

                PostButtons(
                    expanded = expanded,
                    liked = liked,
                    onLikeButtonClick = { /*TODO*/ },
                    onCommentsButtonClick = { /*TODO*/ },
                    onExpandButtonClick = { expanded = !expanded },
                    modifier = modifier.weight(1f)
                )
            }
            if (expanded) {
                Text(
                    text = post.review,
                    modifier = modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun PostButtons(
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
fun PostInfo(
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PostItemPreview() {
    BookForumTheme {
        PostItem(
            post = Post(
                id = 1,
//                title = "A Game of Thrones",
                title = "Harry Potter and the Sorcerer's Stone",
                author = "George R. R. Martin",
                published = "01.08.1996",
                review = "\"Game of Thrones\" is a captivating introduction to Martin's epic saga," +
                        "blending elements of fantasy, political intrigue, and human drama into " +
                        "a spellbinding tale that will leave readers eager for more." +
                        "Whether you're a fan of fantasy or simply enjoy a compelling story, " +
                        "this book is sure to captivate and enthrall."
            )
        )
    }
}