package com.mike.moviehubdemo.components

import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mike.moviehubdemo.model.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun MovieDetailCard(
    movieItem: Movie,
    fs_db: FirebaseFirestore
) {

    var movieIconState:Boolean by remember{
        mutableStateOf(false)
    }
    var lastInsertedDocument by remember {
        mutableStateOf<DocumentReference?>(null)
    }

    Column(
        modifier = Modifier
            //.border(2.dp, Color.Black, shape = CircleShape)
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .fillMaxHeight()
            //.height(80.dp)
            .padding(5.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            model = ImageRequest.Builder(
                LocalContext.current
            )
                .data("https://image.tmdb.org/t/p/w500/${movieItem.posterPath}")
                .build(),
            contentDescription = movieItem.overview
        )
        Column(Modifier.padding(20.dp)) {
            movieItem.originalTitle?.let {
                Text(
                    color = Color.White,
                    text = it,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
            } // end Text
            Spacer(modifier = Modifier.padding(5.dp))
            movieItem.releaseDate?.let {
                Text(
                    text = "Release Date: $it",
                    modifier = Modifier.padding(end = 8.dp),
                    maxLines = 9,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            movieItem.overview?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(end = 8.dp),
                    maxLines = 9,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {

                movieItem.voteAverage?.let {
                    Text(
                        text = "Avg Vote: $it",
                        modifier = Modifier.padding(end = 8.dp),
                        maxLines = 9,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
                movieItem.voteCount?.let {
                    Text(
                        text = "# of Votes: $it",
                        modifier = Modifier.padding(end = 8.dp),
                        maxLines = 9,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                        movieIconState = !movieIconState
                        val library: CollectionReference =
                            FirebaseFirestore.getInstance().collection("movies_library")
                        var movieExists: Boolean? = null

                        val movie = hashMapOf(
                            "movie_id" to "${movieItem.id}",
                            "movie_title" to "${movieItem.title}",
                            "movie_overview" to "${movieItem.overview}",
                            "movie_poster_path" to "${movieItem.posterPath}",
                            "movie_release_date" to "${movieItem.releaseDate}",
                            "movie_popularity" to "${movieItem.popularity}",
                            "movie_avg_vote" to "${movieItem.voteAverage}",
                            "movie_vote_count" to "${movieItem.voteCount}"
                        )
                        GlobalScope.launch {
                            movieExists = doesMovieExist(movieItem.id.toString(), library)

                            if (movieIconState) {

                                if (movieExists == true) {
                                    Log.d(
                                        "FS",
                                        "Sorry but you can't insert the same move. Please try again."
                                    )
                                } else {
                                    fs_db.collection("movies_library")
                                        .add(movie)
                                        .addOnSuccessListener { documentReference ->
                                            // todo - keep track of last inserted document
                                            lastInsertedDocument = documentReference
                                            Log.d(
                                                "FS",
                                                "DocumentSnapshot added with ID: ${documentReference.id}"
                                            )
                                        }
                                        .addOnFailureListener { e ->
                                            Log.d("FS", "Error adding document", e)
                                        }
                                }
                            } else if (!movieIconState) {
                                lastInsertedDocument?.delete()
                                    ?.addOnSuccessListener { e ->
                                        Log.i(
                                            "Removal",
                                            "THERE WAS A PROBLEM REMOVING THE RECORD FROM FS ${e}"
                                        )
                                    }
                            }
                        }
                    }, //end onclick
                ) {
                    Icon(
                        modifier = Modifier
                            .size(10.dp)
                            .scale(2.5f),
                        imageVector = if (movieIconState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "isIconChanged",
                        tint = Color.White


                    )
                }
            }
        }
    }
}
suspend fun doesMovieExist(movieID:String, collection:CollectionReference): Boolean{
    val querySnapshot = collection.whereEqualTo("movie_id", movieID).get().await()
    return !querySnapshot.isEmpty
}
