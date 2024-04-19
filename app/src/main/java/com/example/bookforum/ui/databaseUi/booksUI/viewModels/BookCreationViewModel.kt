package com.example.bookforum.ui.databaseUi.booksUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.BooksRepository
import com.example.bookforum.ui.databaseUi.booksUI.states.BookCreationUiState
import com.example.bookforum.ui.databaseUi.booksUI.states.BookDetails
import com.example.bookforum.ui.databaseUi.booksUI.states.toBook

class BookCreationViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var bookCreationUiState by mutableStateOf(BookCreationUiState())

    fun updateUiState(bookDetails: BookDetails) {
        bookCreationUiState = BookCreationUiState(
            bookDetails = bookDetails,
            areInputsValid = validateInputs(bookDetails)
        )
    }

    suspend fun saveBook() {
        if (validateInputs()) {
            booksRepository.insertBook(bookCreationUiState.bookDetails.toBook())
        }
    }

    private fun validateInputs(bookDetails: BookDetails = bookCreationUiState.bookDetails): Boolean {
        return with(bookDetails) {
            title.isNotBlank() && author.isNotBlank() && published.isNotBlank() && review.isNotBlank()
        }
    }
}