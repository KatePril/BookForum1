package com.example.bookforum.ui.apiScreens.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookforum.ui.ApiUiState

@Composable
fun ApiResultScreen(
    uiState: ApiUiState,
    modifier: Modifier = Modifier
) {
    when(uiState) {
        is ApiUiState.Success -> {
            if (uiState.books != null) {
                SearchResultScreen(
                    books = uiState.books,
                    modifier = modifier
                )
            } else {
                Text(text = "No results found")
            }
        }
        is ApiUiState.Loading -> {
            LoadingResultScreen(modifier)
        }
        is ApiUiState.Error -> {
            ErrorApiScreen(uiState.error.orEmpty(), modifier)
        }
    }
}