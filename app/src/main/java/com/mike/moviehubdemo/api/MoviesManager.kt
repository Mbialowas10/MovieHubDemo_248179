package com.mike.moviehubdemo.api

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mike.moviehubdemo.model.Movie
import com.mike.moviehubdemo.model.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesManager {
    private var _movieResponse = mutableStateOf<List<Movie>>(emptyList())
    //val api_key:String = "105eb33fc0b1d90e4346524a7e7a778e"
    val api_key:String = "df2c0933d248a9c91c0ed01e25054bd1"

    val movieResponse: MutableState<List<Movie>>
        @Composable get() = remember{
            _movieResponse
        }

    init{
        getMovies()
    }

     private fun getMovies(){

         val service = Api.retrofitService.getTrendingMovies(api_key)


        service.enqueue(object : Callback<MovieData>{
            override fun onResponse(
                call: Call<MovieData>,
                response: Response<MovieData>
            ) {
                if (response.isSuccessful){
                    Log.i("mjb", "Data Loaded")
                    Log.i("mike", response.body()?.results.toString())
                    _movieResponse.value = response.body()?.results?: emptyList()
                    Log.i("mjb-array", _movieResponse.value.toString())

                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.d("error", "${t.message}")
            }

        })
    }
}

