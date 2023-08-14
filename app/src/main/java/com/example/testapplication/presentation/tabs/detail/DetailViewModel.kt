package com.example.testapplication.presentation.tabs.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.MovieItem
import com.example.domain.usecases.GetDetailMovieInfoUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getDetailMovieInfoUseCase: GetDetailMovieInfoUseCase
) : ViewModel() {

    private var movieDetailLD = MutableLiveData<MovieItem>()

    fun getDetailInfoMovie(movieId: Int): LiveData<MovieItem> {
        viewModelScope.launch {
           movieDetailLD.postValue(getDetailMovieInfoUseCase.execute(movieId))
        }
        return movieDetailLD
    }


}