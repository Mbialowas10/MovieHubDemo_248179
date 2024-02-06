package com.mike.moviehubdemo.api

import com.mike.moviehubdemo.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("trending/movie/day")
    fun getTrendingMovies(@Query("api_key") apiKey:String): Call<MovieData>

    @GET("search/movie")
    fun searchMovieByName(
        @Query("api_key") apiKey:String,
        @Query("query") query:String) : Call<MovieData>

}