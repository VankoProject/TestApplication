package com.example.domain.domain.usecases

import com.example.domain.domain.MovieRepository

class LoadDataUseCase(private val repository: MovieRepository) {

    suspend fun loadDataToDb() = repository.loadDataToDb()
}