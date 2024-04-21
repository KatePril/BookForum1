package com.example.bookforum.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookforum.ui.apiUi.BooksViewModel
import com.example.bookforum.ui.apiUi.screens.ApiResultScreen
import com.example.bookforum.ui.databaseUi.booksUI.screens.PostsDisplayScreen

@Composable
fun BookForumApp() {
//    RegistrationScreen()
//    LoginScreen()
//    PostCreationScreen()
//    PostsDisplayScreen()
    ApiResultScreen()
}

//@Composable
//fun BookApiApp() {
//    val booksViewModel: BooksViewModel = viewModel()
//    ApiResultScreen(uiState = booksViewModel.uiState, viewModel = booksViewModel)
//}