package com.example.testapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 2,
    entities = [MovieDbModel::class],
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao // single<MovieDao> { MovieDataBase.getInstance(applicationContext()).movieDao() }

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE movie_table ADD COLUMN is_favorite INTEGER NOT NULL DEFAULT 0")
            }
        }

        @Volatile
        private var instance: MovieDataBase? = null
        private const val DB_NAME = "movie_table.db"

        @Synchronized
        fun getInstance(context: Context): MovieDataBase {
            instance?.let {
                return it
            }
            val db = Room.databaseBuilder(
                context, MovieDataBase::class.java, DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()

            instance = db
            return db
        }
    }
}