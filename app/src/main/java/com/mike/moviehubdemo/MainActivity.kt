package com.mike.moviehubdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mike.moviehubdemo.ui.theme.MovieHubDemoTheme

sealed class Destination(val route: String){
    object Movie: Destination("movie")

    object Search: Destination("search")

    object Watch: Destination("watch")

    object MovieDetail: Destination("movieDetail/{movieID}"){
        fun createRoute(movieID: Int?) = "movieDetail/$movieID"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieHubDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    val navController =
                }
            }
        }
    }
}

@Composable
fun MovieScaffold(){
   Scaffold(
       bottomBar={
       }
   ){
       // paddingValues culates the size of the bottomBar
       paddingValues ->
         NavHost
   }
}