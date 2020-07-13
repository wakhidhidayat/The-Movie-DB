package com.wahidhidayat.themoviedb.network

import com.wahidhidayat.themoviedb.model.Credits
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIEndpoints {
    @GET("movie/now_playing")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movies>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movies>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movies>

    @GET("movie/{movie_id}")
    fun getMovie(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>

    @GET("movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String
    ): Call<Credits>
}