package com.mike.moviehubdemo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mike.moviehubdemo.model.Movie
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MovieCard(
    movieItem: Movie,
    navController: NavController
){
    Column(
        modifier = Modifier
            .border(1.dp, Color.Red, shape = RectangleShape)
            .padding(5.dp)
            .clickable {
                // add the ability to go to detail page
                navController.navigate("movieDetail/${movieItem.id}/${movieItem.title}")
            }
    ) {
        Row(
           modifier = Modifier
               .background(color = Color.DarkGray)
               .fillMaxWidth()
               .padding(5.dp)
        ){
            AsyncImage(
                model = ImageRequest.Builder(
                    LocalContext.current
                ).data("https://tmdb.org/t/p/w500/${movieItem.posterPath}")
                    .build(),
                contentDescription = movieItem.overview
            )
            Column(Modifier.padding(20.dp)){
                movieItem.originalTitle?.let{
                    Text(
                        color = Color.White,
                        text = it,
                        modifier = Modifier
                            .padding(top=8.dp, end=8.dp),
                        style= TextStyle(fontSize = 14.sp),
                        maxLines = 1
                    )
                }
                movieItem.overview?.let{
                    Text(
                        text = it,
                        modifier = Modifier.padding(end=8.dp),
                        maxLines = 9,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}