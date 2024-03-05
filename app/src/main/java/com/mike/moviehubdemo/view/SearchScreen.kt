package com.mike.moviehubdemo.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mike.moviehubdemo.api.MovieViewModel
import com.mike.moviehubdemo.db.AppDatabase

@Composable
fun SearchScreen(navController: NavController, viewModel: MovieViewModel){

    MovieSearchForm(navController,viewModel)
}