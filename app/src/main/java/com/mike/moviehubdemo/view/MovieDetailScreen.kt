package com.mike.moviehubdemo.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.mike.moviehubdemo.components.MovieDetailCard
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieDetailScreen(
    movie: Movie?,
    fs_db: FirebaseFirestore
){
    val context: Context = LocalContext.current
    Log.i("MovieTest", movie.toString())

    if (movie != null) {
        MovieDetailCard(movieItem = movie, fs_db )
    }

//    Column(){
//        Text(text="Movie Detail Screen")
//        Text(text="${movieID}")
//        Text(text="${title}")
//
//    }

}