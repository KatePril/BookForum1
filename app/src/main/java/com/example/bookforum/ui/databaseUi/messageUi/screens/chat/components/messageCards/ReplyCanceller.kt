package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.messageCards

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
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
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.buttons.ButtonWithIcon

@Composable
internal fun ReplyCanceller(
    getReplyById: (Int) -> Message?,
    getReplySender: (Int) -> User?,
    updateUiState: (MessageDetails) -> Unit,
    messageDetails: MessageDetails,
    modifier: Modifier = Modifier
) {
    val reply = getReplyById(messageDetails.reply)
    Log.i("REPLY_MSG", reply.toString())
    if (reply != null) {
        val sender = getReplySender(reply.senderId)
        Log.i("REPLY_SENDER", sender.toString())
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
                .fillMaxWidth()
                .absolutePadding(top = 745.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(text = "${sender?.username}: ${reply.text}")
                ButtonWithIcon(
                    imageVector = Icons.Filled.Cancel,
                    onClick = {
                        updateUiState(messageDetails.copy(reply = 0))
                    },
                    tint = MaterialTheme.colorScheme.error,
                    modifier = modifier.size(16.dp)
                )
            }
        }
    }
}