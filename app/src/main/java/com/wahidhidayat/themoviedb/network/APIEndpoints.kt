package com.wahidhidayat.themoviedb.network

import com.wahidhidayat.themoviedb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndpoints {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>
}