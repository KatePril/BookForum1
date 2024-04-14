package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.data.ForumDataContainer
import com.example.bookforum.ui.apiScreens.BooksViewModel
import com.example.bookforum.ui.apiScreens.screens.ApiResultScreen
import com.example.bookforum.ui.databaseScreens.RegistrationScreen
import com.example.bookforum.ui.databaseScreens.UserRegistrationViewModel

@Composable
fun BookForumApp() {
    RegistrationScreen()
    /*TODO delete duplicate user from db*/
}

@Composable
fun BookApiApp() {
    val booksViewModel: BooksViewModel = viewModel()
    ApiResultScreen(uiState = booksViewModel.uiState, booksViewModel = booksViewModel)
}