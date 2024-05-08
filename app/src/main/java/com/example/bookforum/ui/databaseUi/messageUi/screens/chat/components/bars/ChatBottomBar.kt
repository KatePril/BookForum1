package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bookforum.R
import com.example.bookforum.data.entities.Message
import com.example.bookforum.data.entities.User
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageCreationUiState
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.ButtonWithIcon
import com.example.bookforum.ui.screenParts.FormInput

@Composable
fun ChatBottomBar(
    getMessageById: (Int) -> Message?,
    getSenderById: (Int) -> User?,
    messageCreationUiState: MessageCreationUiState,
    onValueChange: (MessageDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.i("REPLY", messageCreationUiState.messageDetails.reply.toString())
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (messageCreationUiState.messageDetails.reply != 0) {
            val reply = getMessageById(messageCreationUiState.messageDetails.reply)
            Log.i("REPLY_MSG", reply.toString())
            if (reply != null) {
                val sender = getSenderById(reply.senderId)
                Log.i("REPLY_SENDER", sender.toString())
                Surface(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.padding(8.dp)
                    ) {
                        Text(text = "${sender?.username}: ${reply.text}")
                        ButtonWithIcon(
                            imageVector = Icons.Filled.Cancel,
                            onClick = {
                                onValueChange(messageCreationUiState.messageDetails.copy(reply = 0))
                            },
                            tint = MaterialTheme.colorScheme.error,
                            modifier = modifier.size(16.dp)
                        )
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .padding(end = dimensionResource(R.dimen.padding_large))
                    .weight(2f)
            ) {
                FormInput(
                    value = messageCreationUiState.messageDetails.text,
                    onValueChange = {
                        onValueChange(messageCreationUiState.messageDetails.copy(text = it))
                    },
                    labelText = R.string.message_input,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    singleLine = false
                )
            }
            Column (
                modifier = modifier
            ) {
                IconButton(
                    onClick = onSendClick,
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small),
                    enabled = messageCreationUiState.isTextValid
                ) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    }

}