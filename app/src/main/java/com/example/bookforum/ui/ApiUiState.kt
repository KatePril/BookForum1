package com.example.bookforum.ui

import com.example.bookforum.model.BookApiObject

sealed interface ApiUiState {
    data class Success(val books: List<BookApiObject>?) : ApiUiState
    object Error: ApiUiState
    object Loading : ApiUiState
}