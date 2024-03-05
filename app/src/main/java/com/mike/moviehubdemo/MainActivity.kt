package com.mike.moviehubdemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.mike.moviehubdemo.api.MovieViewModel
import com.mike.moviehubdemo.api.MoviesManager
import com.mike.moviehubdemo.db.AppDatabase
import com.mike.moviehubdemo.model.Movie
import com.mike.moviehubdemo.ui.theme.MovieHubDemoTheme
import com.mike.moviehubdemo.view.BottomNav
import com.mike.moviehubdemo.view.FavoriteScreen
import com.mike.moviehubdemo.view.MovieDetailScreen
import com.mike.moviehubdemo.view.MovieScreen
import com.mike.moviehubdemo.view.SearchScreen
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

sealed class Destination(val route: String){
    object Movie: Destination("movie")

    object Search: Destination("search")

    object Watch: Destination("watch")

    object MovieDetail: Destination("movieDetail/{movieID}"){
        //fun createRoute(movieID: Int?) = "movieDetail/$movieID"
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
                    // get context
                    var context: Context = LocalContext.current

                    // get database instance
                    val db = AppDatabase.getInstance(context)

                    // fetch movie data from api
                    val moviesManager:MoviesManager = MoviesManager(db)

                    // initialize cloud firestore
                    val fs_db = Firebase.firestore

                    // initialize the MovieViewModel = viewModel()
                    val viewModel: MovieViewModel = viewModel()

                    val navController = rememberNavController()
                    MovieScaffold(navController = navController, moviesManager, db, fs_db, viewModel)


                }
            }
        }
    }
}


@Composable
fun MovieScaffold(navController: NavHostController, moviesManager: MoviesManager, db:AppDatabase, fs_db: FirebaseFirestore, movieViewModel: MovieViewModel){
   var movie by remember {
       mutableStateOf<Movie?>(null)
   }
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
                 MovieScreen(moviesManager,navController, movieViewModel)
             }
             composable(Destination.Watch.route){
                 FavoriteScreen(navController)
             }
             composable(Destination.MovieDetail.route){navBackStackEntry ->
                 //val movie = Movie(id=9999,title="Fake Movie")
                 //navController.currentBackStackEntry?.savedStateHandle?.set("movie",movie)
                 val movie_id:String? = navBackStackEntry.arguments?.getString("movieID")

                 GlobalScope.launch {
                     if (movie_id != null ){
                         movie = db.movieDao().getMovieById(movie_id.toInt())
                     }
                 }
                 navController.currentBackStackEntry?.savedStateHandle?.set("movie",movie)
                 
                 //val movie_title:String? = navBackStackEntry.arguments?.getString("title")
                 Log.i("MovieID", movie_id.toString())
                 if (movie != null){
                     MovieDetailScreen(movie = movie, fs_db)
                }
             // MovieDetailScreen()
             }
             composable(Destination.Search.route){
                 SearchScreen(navController, movieViewModel)

             }

         }
   }
}