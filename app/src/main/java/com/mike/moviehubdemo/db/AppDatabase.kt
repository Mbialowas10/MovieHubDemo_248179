package com.mike.moviehubdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mike.moviehubdemo.model.Movie
import com.mike.moviehubdemo.utility.Converters

@Database(entities=[Movie::class], version=4, exportSchema=false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
    //need reference to DAO object
    abstract fun movieDao() : MovieDao

    companion object{
        @Volatile

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MovieHub Demo Database").addMigrations(MIGRATION_4_5)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
        }
        val MIGRATION_4_5 = object : Migration(4,5){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Your migration logic here
                // For example, you can recreate the table
                database.execSQL("DROP TABLE IF EXISTS tbl_movies")
                database.execSQL(
                    """
                        CREATE TABLE IF NOT EXISTS popular_movies (
                            id INTEGER PRIMARY KEY,
                            isIconChanged BOOLEAN,
                            adult INTEGER,
                            backdropPath TEXT,
                            genreIds TEXT, -- Assuming you want to store the list of genre IDs as a JSON string
                            mediaType TEXT,
                            originalLanguage TEXT,
                            originalTitle TEXT,
                            overview TEXT,
                            popularity REAL,
                            posterPath TEXT,
                            releaseDate TEXT,
                            title TEXT,
                            video INTEGER,
                            voteAverage REAL,
                            voteCount INTEGER
                        )
                    """
                ) // Define your new schema
                database.execSQL("DROP TABLE IF EXISTS searched_movies")
                database.execSQL(
                    """
                        CREATE TABLE IF NOT EXISTS searched_movies (
                            id INTEGER PRIMARY KEY,
                            isIconChanged BOOLEAN,
                            adult INTEGER,
                            backdropPath TEXT,
                            genreIds TEXT, -- Assuming you want to store the list of genre IDs as a JSON string
                            mediaType TEXT,
                            originalLanguage TEXT,
                            originalTitle TEXT,
                            overview TEXT,
                            popularity REAL,
                            posterPath TEXT,
                            releaseDate TEXT,
                            title TEXT,
                            video INTEGER,
                            voteAverage REAL,
                            voteCount INTEGER
                        )
                    """
                )
            }
        }

    }

}