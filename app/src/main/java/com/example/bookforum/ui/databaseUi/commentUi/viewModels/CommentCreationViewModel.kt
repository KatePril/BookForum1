package com.example.bookforum.ui.databaseUi.commentUi.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.UsersRepository

class CommentCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository,
    private val commentsRepository: CommentsRepository
) : ViewModel() {
}