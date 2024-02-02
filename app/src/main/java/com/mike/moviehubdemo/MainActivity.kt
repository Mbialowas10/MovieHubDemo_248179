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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mike.moviehubdemo.api.MoviesManager
import com.mike.moviehubdemo.ui.theme.MovieHubDemoTheme
import com.mike.moviehubdemo.view.BottomNav
import com.mike.moviehubdemo.view.FavoriteScreen
import com.mike.moviehubdemo.view.MovieScreen

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

                    // fetch movie data from api
                    val moviesManager:MoviesManager = MoviesManager()

                    val navController = rememberNavController()
                    MovieScaffold(navController = navController, moviesManager )


                }
            }
        }
    }
}


@Composable
fun MovieScaffold(navController: NavHostController, moviesManager: MoviesManager){
   Scaffold(
       bottomBar={
           BottomNav(navController = navController)
       }
   ){
       // paddingValues calculates the size of the bottomBar
       paddingValues ->
         //NavHost
         NavHost(navController = navController, startDestination = Destination.Movie.route ){
             composable(Destination.Movie.route){
                 MovieScreen(moviesManager,navController)
             }
             composable(Destination.Watch.route){
                 FavoriteScreen()
             }
             composable(Destination.MovieDetail.route){navBackStackEntry ->
                 // MovieDetailScreen()
             }
             composable(Destination.Search.route){
                 SearchSreen()
             }

         }
   }
}