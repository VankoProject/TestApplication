package com.example.testapplication.di

import com.example.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {


    factory<DeleteMovieItemUseCase> {
        DeleteMovieItemUseCase(repository = get())
    }

    factory<GetDetailMovieInfoUseCase> {
        GetDetailMovieInfoUseCase(repository = get())
    }

    factory<GetFavoriteMoviesUseCase> {
        GetFavoriteMoviesUseCase(repository = get())
    }

    factory<GetPopularMoviesUsesCase> {
        GetPopularMoviesUsesCase(repository = get())
    }

    factory<SaveMovieItemUseCase> {
        SaveMovieItemUseCase(repository = get())
    }

    factory<LoadDataUseCase> {
        LoadDataUseCase(repository = get())
    }
}