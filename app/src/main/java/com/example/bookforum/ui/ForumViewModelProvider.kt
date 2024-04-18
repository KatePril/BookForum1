package com.example.bookforum.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookforum.ForumApplication
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserLoginViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserRegistrationViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UsersViewModel

object ForumViewModelProvider {

    val Factory = viewModelFactory {

//        initializer {
//            UserRegistrationViewModel(
//                forumApplication().container.usersRepository
//            )
//        }
//        initializer {
//            UserLoginViewModel(
//                forumApplication().container.usersRepository
//            )
//        }
        initializer {
            UsersViewModel(forumApplication().container.usersRepository)
        }
    }
}

fun CreationExtras.forumApplication(): ForumApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ForumApplication)