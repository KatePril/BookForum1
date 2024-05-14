package com.example.bookforum.ui.databaseUi.messageUi.screens.chat.components.bars

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageCreationUiState
import com.example.bookforum.ui.databaseUi.messageUi.states.MessageDetails
import com.example.bookforum.ui.screenParts.forms.FormInput
import com.example.bookforum.ui.screenParts.forms.TextInputForm

@Composable
fun ChatBottomBar(
    messageCreationUiState: MessageCreationUiState,
    onValueChange: (MessageDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextInputForm(
        enabled = messageCreationUiState.isTextValid,
        onSendClick = onSendClick,
        modifier = modifier
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
}