package com.example.bookforum.ui.databaseUi.messageUi.screens.chatsList

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.ForumViewModelProvider
import com.example.bookforum.ui.databaseUi.messageUi.viewModels.ChatsListViewModel

@Composable
fun ChatsListScreen(
    viewModel: ChatsListViewModel = viewModel(factory = ForumViewModelProvider.Factory)
) {}