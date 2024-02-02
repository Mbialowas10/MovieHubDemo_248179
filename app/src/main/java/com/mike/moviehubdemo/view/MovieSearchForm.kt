package com.mike.moviehubdemo.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieSearchForm(navController: NavController){
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    // todo add in val viewModel:MovieViewModel = viewModel()
    //val movies: List<Movie> = movies.value

    var query by remember { mutableState("")}

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
            keyboardOptions = KeyboardOptions(imeAction= ImeAction.Search)
//            keyboardActions  KeyboardActions(onSearch = {
//                // add view model in a bit
//            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom=16.dp)
        )
    }
}
