package com.example.bookforum.ui.databaseUi.postsUI.states

data class PostCreationUiState (
    val postDetails: PostDetails = PostDetails(),
    val areInputsValid: Boolean = false
)