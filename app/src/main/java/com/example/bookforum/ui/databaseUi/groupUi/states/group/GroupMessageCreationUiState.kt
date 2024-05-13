package com.example.bookforum.ui.databaseUi.groupUi.states.group

data class GroupMessageCreationUiState(
    val groupMessageDetails: GroupMessageDetails = GroupMessageDetails(),
    val isTextValid: Boolean = false
)
