package com.example.bookforum.ui.apiScreens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.model.ResultApiObject
import com.example.bookforum.network.BooksApi
import com.example.bookforum.ui.ApiUiState
import com.example.bookforum.utils.SecretKeys
import kotlinx.coroutines.launch
import java.io.IOException

private const val API_HOST = "getbooksinfo.p.rapidapi.com";

class BooksViewModel : ViewModel() {
    var uiState: ApiUiState by mutableStateOf(ApiUiState.Loading)
        private set
    init {
        getBooks("Theory of Everything")
    }

    private fun getBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = BooksApi.retrofitService.getBooks(query, SecretKeys.XRapidAPIKey, API_HOST)
                val body = response.body()?.results
                uiState = ApiUiState.Success(body);
            } catch (e: IOException) {
                uiState = ApiUiState.Error
            }

        }
    }
}
