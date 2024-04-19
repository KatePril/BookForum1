package com.example.bookforum.ui.databaseUi.booksUI.states

data class BookCreationUiState (
    val bookDetails: BookDetails = BookDetails(),
    val areInputsValid: Boolean = false
)