package com.example.bookforum.ui.databaseUi.postsUI.states

import com.example.bookforum.data.entities.User

data class UserByIdUiState(
    val user: User = User(0, "", "", "")
)

/* TODO replace UserByIdUiState with mutableState of User*/