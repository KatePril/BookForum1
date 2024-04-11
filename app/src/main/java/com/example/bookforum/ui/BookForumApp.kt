package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.apiScreens.BooksViewModel
import com.example.bookforum.ui.apiScreens.screens.ApiResultScreen

@Composable
fun BookForumApp() {
    val booksViewModel: BooksViewModel = viewModel()
    ApiResultScreen(uiState = booksViewModel.uiState)
}