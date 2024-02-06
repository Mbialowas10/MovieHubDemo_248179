package com.mike.moviehubdemo.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mike.moviehubdemo.api.MovieViewModel

@Composable
fun SearchScreen(navController: NavController){
    val movieViewModel: MovieViewModel = viewModel() // fetches data
    MovieSearchForm(navController)
}