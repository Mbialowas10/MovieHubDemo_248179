package com.mike.moviehubdemo.view

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.mike.moviehubdemo.components.MovieCard
import com.mike.moviehubdemo.model.Movie

@Composable
fun FavoriteScreen(navController: NavController){
    var data by remember { mutableStateOf<List<Movie>>(emptyList()) }

    var movie_avg_vote: Any? = null
    var movie_id: Any? = null
    var movie_overview: Any? = null
    var movie_popularity: Any? = null
    var movie_poster_path: Any? = null
    var movie_release_date: Any? = null
    var movie_title: Any? = null
    var movie_vote_count: Any? = null
    var isIconChanged: Any? = null



    Text(text = "Watch Later Screen")

    // reference the movies collection
    val collectionReference = FirebaseFirestore.getInstance().collection("movies_library")
    var movie = Movie()

    collectionReference
        .get()
        .addOnSuccessListener { documents ->
            val dataList = documents.map { documentSnapshot ->
                val dataMap = documentSnapshot.data
                Movie(
                    id = dataMap["movie_id"] as? Int,
                    overview = dataMap["movie_overview"] as? String,
                    voteAverage = dataMap["movie_avg_vote"] as? Double,
                    voteCount =  dataMap["movie_vote_count"] as? Int,
                    popularity = dataMap["movie_popularity"] as? Double,
                    posterPath = dataMap["movie_poster_path"] as? String,
                    releaseDate = dataMap["movie_release_date"] as? String,
                    title = dataMap["movie_title"] as? String
                    //isIconChanged = dataMap["isIconChanged"] as? Boolean


                    // Map other fields as needed
                )
            }
            data = dataList
        }
        .addOnFailureListener { exception ->
            // Handle the failure here
            Log.e("Firestore", "Error getting documents: ", exception)
        }

    LazyColumn{
        items(data){movie ->
            MovieCard(movieItem=movie, navController, viewModel= viewModel() )
        }



    }

}
