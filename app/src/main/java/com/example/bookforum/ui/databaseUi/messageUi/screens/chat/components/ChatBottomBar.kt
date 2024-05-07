package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageCreationUiState
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.FormInput
import com.example.bookforum.ui.screenParts.FormInputWithMessage

@Composable
fun ChatBottomBar(
    messageCreationUiState: MessageCreationUiState,
    onValueChange: (MessageDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                onValueChange = { onValueChange(messageCreationUiState.messageDetails.copy(text = it)) },
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