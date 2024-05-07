package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.theme.BookForumTheme

@Composable
internal fun ReceiverMessageCard(
    user: User,
    message: Message,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Card(
            modifier = modifier
                .weight(2f)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.default_elevation)
            ),
            shape = RoundedCornerShape(
                topStart = dimensionResource(R.dimen.padding_large),
                topEnd = dimensionResource(R.dimen.padding_large),
                bottomEnd = dimensionResource(R.dimen.padding_large)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(
                modifier = modifier.padding(
                    top = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_large),
                    start = dimensionResource(R.dimen.padding_large),
                    end = dimensionResource(R.dimen.padding_large)
                )
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_medium)),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = user.username,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = message.date,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Spacer(modifier = modifier.weight(1f))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReceiverMessageCardPreview() {
    BookForumTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ReceiverMessageCard(
                user = User(0, "anyablue", "", ""),
                message = Message(0, "Hi :)", "12:00 06.05.2024", 0, 0)
            )
        }

    }
}