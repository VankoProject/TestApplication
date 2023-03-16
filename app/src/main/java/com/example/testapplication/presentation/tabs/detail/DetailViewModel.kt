package com.example.testapplication.presentation.tabs.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.data.MovieRepositoryImpl
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.usecases.GetDetailMovieInfoUseCase
import kotlinx.coroutines.launch

class DetailViewModel(context: Context) : ViewModel() {

    private val repository = MovieRepositoryImpl(context)
    private val getDetailMovieInfoUseCase = GetDetailMovieInfoUseCase(repository)

    private var movieDetailLD = MutableLiveData<MovieItem>()

    fun getDetailInfoMovie(movieId: Int): LiveData<MovieItem> {
        viewModelScope.launch {
           movieDetailLD.postValue(getDetailMovieInfoUseCase.execute(movieId))
        }
        return movieDetailLD
    }


}