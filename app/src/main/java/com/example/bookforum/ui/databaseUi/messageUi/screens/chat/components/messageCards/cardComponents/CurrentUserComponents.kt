package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards.cardComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.screenParts.FilledButtonWithIcon

@Composable
internal fun MessageButtons(
    onDeleteClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        FilledButtonWithIcon(
            imageVector = Icons.Filled.Delete,
            onClick = onDeleteClick,
            iconButtonColors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error
            ),
            modifier = modifier
        )
        FilledButtonWithIcon(
            imageVector = Icons.Filled.Edit,
            onClick = onEditButtonClick,
            iconButtonColors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CurrentUserMessageCard(
    onMessageClick: () -> Unit,
    message: Message,
    replyMessage: Message?,
    replySender: User?,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onMessageClick,
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.default_elevation)
        ),
        shape = RoundedCornerShape(
            topStart = dimensionResource(R.dimen.padding_large),
            topEnd = dimensionResource(R.dimen.padding_large),
            bottomStart = dimensionResource(R.dimen.padding_large)
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.padding_medium),
                bottom = dimensionResource(R.dimen.padding_large),
                start = dimensionResource(R.dimen.padding_large),
                end = dimensionResource(R.dimen.padding_large)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_medium)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = message.date,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            if (replyMessage != null && replySender != null) {
                ReplyBlock(
                    username = replySender.username,
                    text = replyMessage.text,
                    surfaceColor = MaterialTheme.colorScheme.tertiaryContainer,
                    textColor = MaterialTheme.colorScheme.secondary
                )
            }
            Text(text = message.text)
            Log.i("EDITED", message.edited.toString())
            if (message.edited == 1) {
                EditedMessage()
            }
        }
    }
}