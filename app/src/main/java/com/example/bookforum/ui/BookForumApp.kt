package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.screens.BooksViewModel
import com.example.bookforum.ui.screens.SampleScreen

@Composable
fun BookForumApp() {
    val booksViewModel: BooksViewModel = viewModel()
    SampleScreen(uiState = booksViewModel.uiState)
}