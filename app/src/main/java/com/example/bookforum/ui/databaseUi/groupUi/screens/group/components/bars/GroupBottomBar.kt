package com.example.bookforum.ui.databaseUi.groupUi.screens.group.components.bars

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.R
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageCreationUiState
import com.example.bookforum.ui.databaseUi.groupUi.states.group.GroupMessageDetails
import com.example.bookforum.ui.screenParts.forms.FormInput
import com.example.bookforum.ui.screenParts.forms.TextInputForm

@Composable
internal fun GroupBottomBar(
    groupMessageCreationUiState: GroupMessageCreationUiState,
    onValueChange: (GroupMessageDetails) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextInputForm(
        enabled = groupMessageCreationUiState.isTextValid,
        onSendClick = onSendClick,
        modifier = modifier
    ) {
        FormInput(
            value = groupMessageCreationUiState.groupMessageDetails.text,
            onValueChange = {
                onValueChange(groupMessageCreationUiState.groupMessageDetails.copy(text = it))
            },
            labelText = R.string.message_input,
            color = MaterialTheme.colorScheme.primaryContainer,
            singleLine = false
        )
    }
}