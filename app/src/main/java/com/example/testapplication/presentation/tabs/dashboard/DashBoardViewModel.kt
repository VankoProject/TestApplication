package com.example.testapplication.presentation.tabs.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.MovieItem
import com.example.domain.usecases.DeleteMovieItemUseCase
import com.example.domain.usecases.GetPopularMoviesUsesCase
import com.example.domain.usecases.LoadDataUseCase
import com.example.domain.usecases.SaveMovieItemUseCase
import kotlinx.coroutines.launch

class DashBoardViewModel(
    getPopularMoviesUsesCase: GetPopularMoviesUsesCase,
    private val saveMovieItemUseCase: SaveMovieItemUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val deleteMovieItemUseCase: DeleteMovieItemUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            loadDataUseCase.loadDataToDb()
        }
    }

    val movieListLD = getPopularMoviesUsesCase.execute()

    suspend fun saveToFavoriteList(movieItem: MovieItem) {
        saveMovieItemUseCase.execute(movieItem)
    }

    suspend fun deleteFavoriteFilm(movieItem: MovieItem) {
        deleteMovieItemUseCase.execute(movieItem)
    }


}