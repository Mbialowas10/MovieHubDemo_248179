package com.mike.moviehubdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mike.moviehubdemo.model.Movie

@Database(entities=[Movie::class], version=3, exportSchema=false)
abstract class AppDatabase: RoomDatabase(){
    //need reference to DAO object

    companion object{
        @Volatile

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MovieHub Demo Database").addMigrations(MIGRATION_3_4)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
        }
        val MIGRATION_3_4 = object : Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                // Your migration logic here
                // For example, you can recreate the table
                database.execSQL("DROP TABLE IF EXISTS popular_movies")
                database.execSQL(
                    """
                        CREATE TABLE IF NOT EXISTS popular_movies (
                            id INTEGER PRIMARY KEY,
                            watch BOOLEAN,
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
            }
        }

    }

}