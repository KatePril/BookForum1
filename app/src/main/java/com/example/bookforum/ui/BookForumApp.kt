package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.apiUi.BooksViewModel
import com.example.bookforum.ui.apiUi.screens.ApiResultScreen
import com.example.bookforum.ui.databaseUi.screens.RegistrationScreen

@Composable
fun BookForumApp() {
    RegistrationScreen()
}

@Composable
fun BookApiApp() {
    val booksViewModel: BooksViewModel = viewModel()
    ApiResultScreen(uiState = booksViewModel.uiState, booksViewModel = booksViewModel)
}