package com.example.bookforum.ui.databaseUi.commentUi.states

data class CommentCreationUiState (
    val commentDetails: CommentDetails = CommentDetails(),
    val isTextValid: Boolean = false
)