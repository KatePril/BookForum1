package com.example.bookforum.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookforum.model.BookApiObject
import com.example.bookforum.model.ResultApiObject
import com.example.bookforum.network.BooksApi
import com.example.bookforum.utils.SecretKeys
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_HOST = "getbooksinfo.p.rapidapi.com";

class BooksViewModel : ViewModel() {
    var uiState: String by mutableStateOf("")
        private set
    var books: List<ResultApiObject> by mutableStateOf(emptyList())
    init {
        getBooks("Theory of Everything")
    }

    private fun getBooks(query: String) {
        viewModelScope.launch {
                val response = BooksApi.retrofitService.getBooks(query, SecretKeys.XRapidAPIKey, API_HOST)

                if (response.isSuccessful) {
                    val body = response.body()?.results?.size
                    uiState = (body ?: "No response body").toString()
                } else {
                    uiState = "Error: ${response.code()}"
                }

        }
    }
}
