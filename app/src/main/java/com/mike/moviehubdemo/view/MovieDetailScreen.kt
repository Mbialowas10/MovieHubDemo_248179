package com.mike.moviehubdemo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieDetailScreen(movieID:String,title:String){
    Column(){
        Text(text="Movie Detail Screen")
        Text(text="${movieID}")
        Text(text="${title}")
    }

}