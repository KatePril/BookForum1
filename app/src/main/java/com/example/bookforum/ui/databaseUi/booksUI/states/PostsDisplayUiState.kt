package com.example.bookforum.ui.databaseUi.booksUI.states

import com.example.bookforum.data.entities.Post

data class PostsDisplayUiState(
    val postsList: List<Post> = listOf()
)