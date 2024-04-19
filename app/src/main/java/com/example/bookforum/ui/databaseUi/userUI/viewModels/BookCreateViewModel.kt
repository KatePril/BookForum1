package com.example.bookforum.ui.databaseUi.userUI.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.bookforum.data.repositories.BooksRepository
import com.example.bookforum.ui.databaseUi.booksUI.states.BookCreateUiState
import com.example.bookforum.ui.databaseUi.booksUI.states.BookDetails
import com.example.bookforum.ui.databaseUi.booksUI.states.toBook

class BookCreateViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var bookCreateUiState by mutableStateOf(BookCreateUiState())

    suspend fun saveBook() {
        if (validateInputs()) {
            booksRepository.insertBook(bookCreateUiState.bookDetails.toBook())
        }
    }

    private fun validateInputs(bookDetails: BookDetails = bookCreateUiState.bookDetails): Boolean {
        return with(bookDetails) {
            title.isNotBlank() && author.isNotBlank() && published.isNotBlank() && review.isNotBlank()
        }
    }
}