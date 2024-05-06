package com.example.bookforum.ui.databaseUi.messageUi.states

data class MessageCreationUiState(
    val messageDetails: MessageDetails = MessageDetails(),
    val isTextValid: Boolean = false
)
