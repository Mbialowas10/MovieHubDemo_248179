package com.mike.moviehubdemo.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.mike.moviehubdemo.db.AppDatabase
import com.mike.moviehubdemo.model.Movie
import com.mike.moviehubdemo.model.MovieData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    val movies = mutableStateOf<List<Movie>>(emptyList())

    val moviesResponse: MutableState<List<Movie>> // state allows us to make data available within app
        @Composable get() = remember{
            movies
        }

        val api_key:String = "df2c0933d248a9c91c0ed01e25054bd1"

        fun searchMovies(movieName: String, database: AppDatabase){
            if (movieName.isNotBlank()){
                // this is how we call the search Movie endpoint specified in MoviesService interface (api call)
                val service = Api.retrofitService.searchMovieByName(api_key,movieName)
                service.enqueue(object : Callback<MovieData>{
                    override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                        if (response.isSuccessful){
                            Log.i("MJB", "testing testing")
                            movies.value = response.body()?.results?:emptyList()
                            Log.i("MovieFound", movies.toString())

                            // good place to insertMovies into local DB?
                            GlobalScope.launch {
                                database.movieDao().insertAllMovies(movies=movies.value)
                            }


                        }
                    }

                    override fun onFailure(call: Call<MovieData>, t: Throwable) {
                        Log.d("error", "${t.message}")
                    }
                })


            }else{
                movies.value = emptyList()
            }
        }



}