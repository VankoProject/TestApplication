package com.example.testapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testapplication.domain.MovieItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getFavoriteMovie(): LiveData<List<MovieDbModel>>

    @Query("SELECT * FROM movie_table WHERE id =:movieId")
    suspend fun getDetailInfoMovie(movieId: Int): MovieDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieDbModel: MovieDbModel)

    @Delete
    suspend fun deleteMovie(movieDbModel: MovieDbModel)

}