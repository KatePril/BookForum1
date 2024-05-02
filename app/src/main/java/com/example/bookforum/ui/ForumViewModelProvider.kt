package com.example.bookforum.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookforum.ForumApplication
import com.example.bookforum.ui.apiUi.BooksViewModel
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.PostCreationViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.FeedViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.ProfileViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel

object ForumViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            BooksViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            RegistrationViewModel(
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            LoginViewModel(
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            ProfileViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            PasswordChangeViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            PostCreationViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                postsRepository = forumApplication().container.postsRepository,
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            FeedViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                postsRepository = forumApplication().container.postsRepository
            )
        }
        initializer {
            CommentViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository,
                commentsRepository = forumApplication().container.commentsRepository
            )
        }

    }
}

fun CreationExtras.forumApplication(): ForumApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ForumApplication)