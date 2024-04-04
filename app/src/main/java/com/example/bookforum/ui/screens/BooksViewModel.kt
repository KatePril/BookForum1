package com.example.bookforum.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getBooks("Theory of Everything")
    }

    private fun getBooks(query: String) {
        viewModelScope.launch {
            BooksApi.retrofitService.getBooks(query, SecretKeys.XRapidAPIKey, API_HOST)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            val body = response.body()?.string()
                            uiState = body ?: "No response body"
                        } else {
                            uiState = "Error: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        uiState = "Network request failed: ${t.message}"
                    }
                })
        }
    }
}
