package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DeleteMovieItemUseCaseTest {

    @Test
    fun `deleteMovie() method should change status isFavorite on item in database` () = runBlocking {
        val repository = mockk<MovieRepository>()
        val useCase = DeleteMovieItemUseCase(repository)
        val item = MovieItem(
            1,
            "1",
            "1",
            "1",
            "1",
            1.1,
            true
        )

        coEvery { repository.deleteMovie(item) } just Runs

        useCase.execute(item)

        coVerify(exactly = 1) { repository.deleteMovie(item) }



    }



}