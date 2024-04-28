package com.example.bookforum.ui.databaseUi.commentUi.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.CommentsRepository
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.commentUi.screens.CommentPageDestination
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserByIdViewModel

class CommentCreationViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository,
    private val commentsRepository: CommentsRepository
) : ViewModel() {
}