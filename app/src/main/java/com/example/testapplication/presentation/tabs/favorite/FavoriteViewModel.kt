package com.example.testapplication.presentation.tabs.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.testapplication.data.MovieRepositoryImpl
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.domain.usecases.DeleteMovieItemUseCase
import com.example.testapplication.domain.usecases.GetFavoriteMoviesUseCase

class FavoriteViewModel(context: Context): ViewModel() { // GKR: do not use context in viewModel

    private val repository = MovieRepositoryImpl(context)
    private val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(repository)
    private val deleteMovieItemUseCase = DeleteMovieItemUseCase(repository)

    val movieFavoriteListLD = getFavoriteMoviesUseCase.execute()

    suspend fun deleteFavoriteMovie(movieItem: MovieItem) {
        deleteMovieItemUseCase.execute(movieItem)
    }
}