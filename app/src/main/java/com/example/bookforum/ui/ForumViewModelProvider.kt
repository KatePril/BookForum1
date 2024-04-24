package com.example.bookforum.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookforum.ForumApplication
import com.example.bookforum.ui.apiUi.PostsViewModel
import com.example.bookforum.ui.databaseUi.booksUI.viewModels.PostCreationViewModel
import com.example.bookforum.ui.databaseUi.booksUI.viewModels.PostsDisplayViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel

object ForumViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            PostsViewModel(
                this.createSavedStateHandle(),
                forumApplication().container.usersRepository
            )
        }
        initializer {
            RegistrationViewModel(
                forumApplication().container.usersRepository
            )
        }
        initializer {
            LoginViewModel(
                forumApplication().container.usersRepository
            )
        }
        initializer {
            PostCreationViewModel(
                this.createSavedStateHandle(),
                forumApplication().container.postsRepository,
                forumApplication().container.usersRepository
            )
        }
        initializer {
            PostsDisplayViewModel(
                this.createSavedStateHandle(),
                forumApplication().container.postsRepository,
                forumApplication().container.usersRepository
            )
        }
    }
}

fun CreationExtras.forumApplication(): ForumApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ForumApplication)