package com.example.bookforum.ui.databaseUi.commentUi.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.data.entities.Comment
import com.example.bookforum.data.entities.User
import com.example.compose.BookForumTheme

@Composable
internal fun CommentCard(
    comment: Comment,
    user: User,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            CommentHeader(
                username = user.username,
                date = comment.date,
                modifier = modifier
            )
            Divider(
                modifier = modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = comment.text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun CommentHeader(
    username: String,
    date: String,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        /*TODO fix usernames display*/
        Text(
            text = username,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = date,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CommentCardPreview() {
    BookForumTheme {
        CommentCard(
            comment = Comment(
                0,
                "19:10, 29.04.2024",
                "Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit. In pulvinar dictum magna non malesuada.",
                0,
                0
            ),
            user = User(0, "anyablue", "", "")
        )
    }
}