package com.example.bookforum.ui.apiUi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.network.BooksApi
import com.example.bookforum.ui.databaseUi.likedPostsUI.screens.LikedPostsPageDestination
import com.example.bookforum.utils.API_HOST
import com.example.bookforum.utils.XRapidAPIKey
import kotlinx.coroutines.launch
import java.io.IOException


class BooksViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userId: Int = checkNotNull(savedStateHandle[LikedPostsPageDestination.userIdArg])
    var uiState: ApiUiState by mutableStateOf(ApiUiState.NotEntered)
        private set

    fun getBooks(query: String) {
        uiState = ApiUiState.Loading
        viewModelScope.launch {
            uiState = try {
                val response = BooksApi.retrofitService.getBooks(query, XRapidAPIKey, API_HOST)
                val body = response.body()?.results
                ApiUiState.Success(body)
            } catch (e: IOException) {
                ApiUiState.Error(e.message)
            }

        }
    }
}
