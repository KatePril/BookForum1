package com.example.bookforum.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private const val BASE_URL = "https://getbooksinfo.p.rapidapi.com/";

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .client(OkHttpClient.Builder().build())
    .build()

interface BooksApiService {
    @GET("/")
    fun getBooks(
        @Query("s") query: String,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") apiHost: String
    ): Call<ResponseBody>
}

object BooksApi {
    val retrofitService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}

