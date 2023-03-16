package com.example.testapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [MovieDbModel::class],
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao // single<MoviewDao> { MovieDataBase.getInstance(applicationContext()).moviewDao() }

    companion object {

        @Volatile
        private var INSTANCE: MovieDataBase? = null
        private const val DB_NAME = "movie_table.db"

        @Synchronized
        fun getInstance(context: Context): MovieDataBase {
            INSTANCE?.let {
                return it
            }
            val db = Room.databaseBuilder(
                context, MovieDataBase::class.java, DB_NAME)
                .build()

            INSTANCE = db
            return db
        }
    }
}