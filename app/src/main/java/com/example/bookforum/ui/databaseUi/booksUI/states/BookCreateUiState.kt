package com.example.bookforum.ui.databaseUi.booksUI.states

data class BookCreateUiState (
    val bookDetails: BookDetails = BookDetails(),
    val areInputsValid: Boolean = false
)