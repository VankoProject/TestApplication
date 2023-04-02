package com.example.testapplication.di

import com.example.testapplication.data.MovieRepositoryImpl
import com.example.testapplication.data.database.MovieDataBase
import com.example.testapplication.data.network.retrofit.ApiFactory
import com.example.testapplication.data.network.retrofit.ApiService
import com.example.domain.domain.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit



val dataModule = module {

    single {
        MovieDataBase.getInstance(get())
    }

    single { ApiFactory.createRetrofit() }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }


    single<MovieRepository> {
        MovieRepositoryImpl(
            movieDao = get<MovieDataBase>().movieDao(),
            apiService = get(),
        )
    }
}
