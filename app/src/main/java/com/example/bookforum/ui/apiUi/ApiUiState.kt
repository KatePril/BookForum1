package com.example.bookforum.ui.apiUi

import com.example.bookforum.model.BookApiObject

sealed interface ApiUiState {
    data class Success(val books: List<BookApiObject>?) : ApiUiState
    data class Error(val error: String?): ApiUiState
    object Loading: ApiUiState
    object NotEntered: ApiUiState
}