package com.mike.moviehubdemo.components

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
import com.mike.moviehubdemo.model.Movie

@Composable
fun MovieDetailCard(
    movieItem: Movie
    //title:String
) {
    var movieIconState:Boolean by remember{
        mutableStateOf(false)
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
                    }
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
