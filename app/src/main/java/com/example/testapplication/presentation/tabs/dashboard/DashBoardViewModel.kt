package com.example.testapplication.presentation.tabs.dashboard

import  android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.MovieRepositoryImpl
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.usecases.GetPopularMoviesUsesCase
import com.example.testapplication.domain.usecases.SaveMovieItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashBoardViewModel(context: Context) : ViewModel() {

    private val repository = MovieRepositoryImpl(context)
    private val getPopularMoviesUsesCase = GetPopularMoviesUsesCase(repository)
    private val saveMovieItemUseCase = SaveMovieItemUseCase(repository)

    private var _movieListLD = MutableLiveData<List<MovieItem>>()
    val movieListLD: LiveData<List<MovieItem>> = _movieListLD

    fun getPopularMovies(): LiveData<List<MovieItem>> {
        viewModelScope.launch(Dispatchers.Main) {
            _movieListLD.postValue(getPopularMoviesUsesCase.execute())
        }
        return movieListLD
    }


    suspend fun saveToFavoriteList(movieItem: MovieItem) {
        saveMovieItemUseCase.execute(movieItem)
    }



}