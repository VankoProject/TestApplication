package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.MovieRepository

class LoadDataUseCase (private val repository: MovieRepository) {

    suspend fun loadDataToDb() = repository.loadDataToDb()
}