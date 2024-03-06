package com.mike.moviehubdemo.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mike.moviehubdemo.api.MovieViewModel
import com.mike.moviehubdemo.api.MoviesManager
import com.mike.moviehubdemo.components.MovieCard

@Composable
fun MovieScreen(moviesManager: MoviesManager, navController: NavController,viewModel: MovieViewModel, user:String){

    val movies = moviesManager.movieResponse.value
    Log.i("mjb", movies.toString())
    //Text(text="Movie Screen")

    for(movie in movies){
        Log.i("name", "${movie.title}")
    }
    Column {
        Row{
            Text(text = "Welcome back: $user")
        }
        LazyColumn{
            items(movies){ movie->
                MovieCard(movieItem=movie, navController, viewModel)
            }

        }
    }


}