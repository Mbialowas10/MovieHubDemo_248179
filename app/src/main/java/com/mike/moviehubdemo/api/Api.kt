package com.mike.moviehubdemo.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    // convert json response into objects that the project models can understand
    private val moshi  = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // initialization of retrofit instance
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MoviesService by lazy{
        // instantiate our retrofit service ie. the movieService
        retrofit.create(MoviesService::class.java)
    }

}