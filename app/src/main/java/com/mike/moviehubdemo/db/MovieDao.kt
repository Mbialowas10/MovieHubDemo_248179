package com.mike.moviehubdemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mike.moviehubdemo.model.Movie


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>?)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int):Movie
}