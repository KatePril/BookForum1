package com.example.bookforum.network

import com.example.bookforum.model.ResultApiObject
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://getbooksinfo.p.rapidapi.com/";

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient.Builder().build())
    .build()

interface BooksApiService {
    @GET("/")
    suspend fun getBooks(
        @Query("s") query: String,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): List<ResultApiObject>
}

object BooksApi {
    val retrofitService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}

