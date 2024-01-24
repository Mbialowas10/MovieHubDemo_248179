package com.mike.moviehubdemo.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.mike.moviehubdemo.Destination
import com.mike.moviehubdemo.R

@Composable
fun BottomNav(navController: NavController){
    BottomNavigation(elevation = 7.dp) {
        val navBackStackEntry = navController.currentBackStackEntry
        val currentDestination = navBackStackEntry?.destination //maybe comeback?

        val ic_movie = painterResource(id = R.drawable.ic_movie)
        val ic_watch = painterResource(id = R.drawable.ic_watch)

        BottomNavigationItem(
            selected = currentDestination?.route == Destination.Movie.route,
            onClick = {
                      navController.navigate(Destination.Movie.route){
                          popUpTo(Destination.Movie.route)
                          launchSingleTop = true
                      }
            },
            icon = {
                Icon(painter = ic_movie, contentDescription = "Movie Home Screen")
            },
            label = { Text(text = Destination.Movie.route) }
                
            )
            BottomNavigationItem(
                selected = currentDestination?.route == Destination.Watch.route,
                onClick = {
                    navController.navigate(Destination.Watch.route){
                        popUpTo(Destination.Watch.route)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(painter = ic_watch, contentDescription = "Watch Screen")
                },
                label = { Text(text = Destination.Watch.route) }

            )
    }

}
