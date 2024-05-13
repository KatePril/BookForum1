package com.example.bookforum.ui.databaseUi.groupUi.states

data class GroupCreationUiState(
    val groupDetails: GroupDetails = GroupDetails(),
    val isTitleValid: Boolean = false
)
