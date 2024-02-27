package com.mike.moviehubdemo.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mike.moviehubdemo.components.MovieDetailCard
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieDetailScreen(
    movie: Movie?
){
    val context: Context = LocalContext.current
    Log.i("MovieTest", movie.toString())

    if (movie != null) {
        MovieDetailCard(movieItem = movie)
    }

//    Column(){
//        Text(text="Movie Detail Screen")
//        Text(text="${movieID}")
//        Text(text="${title}")
//
//    }

}