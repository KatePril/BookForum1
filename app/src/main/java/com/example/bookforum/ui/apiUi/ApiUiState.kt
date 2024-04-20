package com.example.bookforum.ui.apiUi

import com.example.bookforum.network.apiObjects.Book

sealed interface ApiUiState {
    data class Success(val books: List<Book>?) : ApiUiState
    data class Error(val error: String?): ApiUiState
    object Loading: ApiUiState
    object NotEntered: ApiUiState
}