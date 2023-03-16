package com.example.testapplication.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.testapplication.data.database.MovieDataBase
import com.example.testapplication.data.database.MovieDbModel
import com.example.testapplication.data.mapper.Mapper
import com.example.testapplication.data.network.model.MovieListDto
import com.example.testapplication.data.network.retrofit.ApiFactory
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.MovieRepository

class MovieRepositoryImpl(
    private val context: Context
) : MovieRepository {

    private val apiService = ApiFactory.apiService
    private val movieDao = MovieDataBase.getInstance(context).movieDao()
    private val mapper = Mapper()

    override fun getFavoriteMovies(): LiveData<List<MovieItem>> {
        return Transformations.map(movieDao.getFavoriteMovie()) { it ->
            it.map { mapper.dbModelToMovieItem(it) }
        }
    }

    override suspend fun getDetailMovieInfo(movieId: Int): MovieItem {
        val detail = movieDao.getDetailInfoMovie(movieId).run {
            mapper.dbModelToMovieItem(this)
        }
        return detail
    }

    override suspend fun getPopularMovies(): List<MovieItem> {
        val listDtoResponse = apiService.getPopularMovies()
        val dto: MovieListDto
        if(listDtoResponse.isSuccessful && listDtoResponse.body()!=null){
            dto = checkNotNull(listDtoResponse.body())
        }else{
            return emptyList()
        }
        val listMovie = dto.results.map {
            mapper.modelDtoToMovieItem(it)
        }
        return listMovie
    }

    override suspend fun saveMovie(movieItem: MovieItem) {
        val itemDb = mapper.movieItemToDbModel(movieItem)
        movieDao.saveMovie(itemDb)
    }

    override suspend fun deleteMovie(movieItem: MovieItem) {
        movieDao.deleteMovie(mapper.movieItemToDbModel(movieItem))
    }
}


