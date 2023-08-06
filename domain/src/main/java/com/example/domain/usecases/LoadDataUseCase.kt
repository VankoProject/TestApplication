package com.example.domain.usecases

import com.example.domain.MovieRepository

class LoadDataUseCase(private val repository: MovieRepository) {

    suspend fun loadDataToDb() = repository.loadDataToDb()
}