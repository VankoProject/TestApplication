package com.example.testapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE is_favorite = true")
    fun getFavoriteMovie(): LiveData<List<MovieDbModel>>

    @Query("SELECT * FROM movie_table")
    fun getPopularMovies(): LiveData<List<MovieDbModel>>

    @Query("SELECT * FROM movie_table WHERE id =:movieId")
    suspend fun getDetailInfoMovie(movieId: Int): MovieDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList (movieListDb: List<MovieDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieDbModel: MovieDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun deleteMovie(movieDbModel: MovieDbModel)

}