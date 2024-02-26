package com.mike.moviehubdemo.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieDetailCard(
    movieItem: Movie
    //title:String
){
    Column(
        modifier = Modifier
            .border(1.dp, Color.Red, shape = RectangleShape)
            .padding(5.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            model = ImageRequest.Builder(
                LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500/${movieItem.posterPath}")
                .build() ,
            contentDescription = movieItem.overview
        )
    }

}