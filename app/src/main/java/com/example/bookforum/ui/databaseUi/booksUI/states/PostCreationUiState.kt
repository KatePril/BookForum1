package com.example.bookforum.ui.databaseUi.booksUI.states

data class PostCreationUiState (
    val postDetails: PostDetails = PostDetails(),
    val areInputsValid: Boolean = false
)