package com.mike.moviehubdemo.api

import com.mike.moviehubdemo.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/trending/movie/day")
    fun getTrendingMovies(@Query("api_key") apiKey:String): Call<MovieData>
}