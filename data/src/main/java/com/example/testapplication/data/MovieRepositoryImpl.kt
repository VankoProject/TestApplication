package com.example.testapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.domain.MovieItem
import com.example.domain.MovieRepository
import com.example.testapplication.data.database.MovieDao
import com.example.testapplication.data.mapper.changeMovieStatus
import com.example.testapplication.data.mapper.dbModelToMovieItem
import com.example.testapplication.data.mapper.modelDtoToMovieDbModel
import com.example.testapplication.data.mapper.movieItemToDbModel
import com.example.testapplication.data.network.model.MovieListDto
import com.example.testapplication.data.network.retrofit.ApiService

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getFavoriteMovies(): LiveData<List<MovieItem>> { // observe
        return Transformations.map(movieDao.getFavoriteMovie()) { it ->
            it.map { dbModelToMovieItem(it) }
        }
    }

    override suspend fun getDetailMovieInfo(movieId: Int): MovieItem {
        val detail = movieDao.getDetailInfoMovie(movieId).run {
            dbModelToMovieItem(this)
        }
        return detail
    }

    override fun getPopularMovies(): LiveData<List<MovieItem>> {
        return Transformations.map(movieDao.getPopularMovies()) { it ->
            it.map { dbModelToMovieItem(it) }
        }
    }

    override suspend fun saveMovie(movieItem: MovieItem) {
        val itemDb = movieItemToDbModel(movieItem)
        movieDao.saveMovie(itemDb)
    }

    override suspend fun deleteMovie(movieItem: MovieItem) {
        movieDao.deleteMovie(changeMovieStatus(movieItem))
    }

    override suspend fun loadDataToDb() {
        var page = 1
        while (page < 10) {
            try {
                val listDtoResponse = apiService.getPopularMovies(page = page)
                val dto: MovieListDto
                if (listDtoResponse.isSuccessful) {
                    dto = checkNotNull(listDtoResponse.body())
                } else {
                    throw java.lang.RuntimeException("error loading")
                }
                val movieListDb = dto.results.map { it ->
                    it.modelDtoToMovieDbModel
                }
                movieDao.insertMovieList(movieListDb)
                ++page
            } catch (_: Exception) {
            }
        }
    }

    override suspend fun searchMovie(title: String): MovieItem {
        TODO("Not yet implemented")
    }
}


