package com.mike.moviehubdemo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mike.moviehubdemo.api.MovieViewModel
import com.mike.moviehubdemo.components.MovieCard
import com.mike.moviehubdemo.model.Movie


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MovieSearchForm(navController: NavController){
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: MovieViewModel = viewModel()
    val movies: List<Movie> = viewModel.movies.value // get searched movies


    var query by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value =  query,
            onValueChange = { query = it },
            label = {Text("Search for a movie")},
            keyboardOptions = KeyboardOptions(imeAction= ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                // add view model in a bit
              viewModel.searchMovies(query)
              keyboardController?.hide()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Button(
            onClick ={
                // view model to go next
                viewModel.searchMovies(query)
            }
        ){
            Text("Search")
        }
        // thread aka corountine in android
        LaunchedEffect(viewModel){
            viewModel.searchMovies(query)
        }

        LazyColumn{
            items(movies){ movie ->
                MovieCard(movieItem = movie, navController = navController)

            }
        }

    }
}
