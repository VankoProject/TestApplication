package com.example.testapplication.di

import com.example.testapplication.presentation.tabs.dashboard.DashBoardViewModel
import com.example.testapplication.presentation.tabs.detail.DetailViewModel
import com.example.testapplication.presentation.tabs.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel<DashBoardViewModel> {
        DashBoardViewModel(
            getPopularMoviesUsesCase = get(),
            saveMovieItemUseCase = get(),
            loadDataUseCase = get(),
            deleteMovieItemUseCase = get()
        )
    }

    viewModel<FavoriteViewModel> {
        FavoriteViewModel(
            getFavoriteMoviesUseCase = get(),
            deleteMovieItemUseCase = get()
        )
    }

    viewModel<DetailViewModel> {
        DetailViewModel(
            getDetailMovieInfoUseCase = get()
        )
    }


}