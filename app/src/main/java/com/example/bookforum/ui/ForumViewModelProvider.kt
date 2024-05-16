package com.example.bookforum.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookforum.ForumApplication
import com.example.bookforum.ui.apiUi.BooksViewModel
import com.example.bookforum.ui.databaseUi.commentUi.viewModels.CommentViewModel
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupCreationViewModel
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupEditViewModel
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupViewModel
import com.example.bookforum.ui.databaseUi.groupUi.viewModels.GroupsListViewModel
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsListViewModel
import com.example.bookforum.ui.databaseUi.likedPostsUI.viewModels.LikedPostsViewModel
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatViewModel
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatsListViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.EditPostViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.FeedViewModel
import com.example.bookforum.ui.databaseUi.postsUI.viewModels.PostCreationViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.LoginViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.PasswordChangeViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.ProfileViewModel
import com.example.bookforum.ui.databaseUi.userUI.viewModels.RegistrationViewModel

object ForumViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            BooksViewModel(
                savedStateHandle = this.createSavedStateHandle()
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
            LikedPostsViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                likedPostsRepository = forumApplication().container.likedPostsRepository
            )
        }
        initializer {
            LikedPostsListViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                likedPostsRepository = forumApplication().container.likedPostsRepository,
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
        initializer {
            EditPostViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                postsRepository = forumApplication().container.postsRepository
            )
        }
        initializer {
            ChatsListViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            ChatViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                messagesRepository = forumApplication().container.messagesRepository,
                usersRepository = forumApplication().container.usersRepository
            )
        }
        initializer {
            GroupsListViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                groupsRepository = forumApplication().container.groupsRepository
            )
        }
        initializer {
            GroupCreationViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                usersRepository = forumApplication().container.usersRepository,
                groupsRepository = forumApplication().container.groupsRepository,
                groupMembersRepository = forumApplication().container.groupMembersRepository
            )
        }
        initializer {
            GroupViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                groupMessageRepository = forumApplication().container.groupMessageRepository
            )
        }
        initializer {
            GroupEditViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                groupMembersRepository = forumApplication().container.groupMembersRepository
            )
        }
    }
}

fun CreationExtras.forumApplication(): ForumApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ForumApplication)