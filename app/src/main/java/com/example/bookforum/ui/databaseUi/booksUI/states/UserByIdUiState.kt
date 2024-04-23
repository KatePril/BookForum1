package com.example.bookforum.ui.databaseUi.booksUI.states

import com.example.bookforum.data.entities.User

data class UserByIdUiState(
    val user: User = User(0, "", "", "")
)