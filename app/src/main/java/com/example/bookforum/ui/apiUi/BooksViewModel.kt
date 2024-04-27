package com.example.bookforum.ui.apiUi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.bookforum.data.repositories.UsersRepository
import com.example.bookforum.network.BooksApi
import com.example.bookforum.ui.databaseUi.userUI.viewModels.UserByIdViewModel
import com.example.bookforum.utils.API_HOST
import com.example.bookforum.utils.SecretKeys
import kotlinx.coroutines.launch
import java.io.IOException


class BooksViewModel(
    savedStateHandle: SavedStateHandle,
    private val usersRepository: UsersRepository
) : UserByIdViewModel(savedStateHandle, usersRepository) {

    var uiState: ApiUiState by mutableStateOf(ApiUiState.NotEntered)
        private set

    fun getBooks(query: String) {
        uiState = ApiUiState.Loading
        viewModelScope.launch {
            uiState = try {
                val response = BooksApi.retrofitService.getBooks(query, SecretKeys.XRapidAPIKey, API_HOST)
                val body = response.body()?.results
                ApiUiState.Success(body)
            } catch (e: IOException) {
                ApiUiState.Error(e.message)
            }

        }
    }
}
