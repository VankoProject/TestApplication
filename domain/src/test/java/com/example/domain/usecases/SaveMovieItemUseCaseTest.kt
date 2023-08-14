package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SaveMovieItemUseCaseTest {

    @Test
    fun `saveMovie() method should save movieItem to database` () = runBlocking {
        val repository = mockk<MovieRepository>()
        val useCase = SaveMovieItemUseCase(repository)
        val item = MovieItem(
            1,
            "1",
            "1",
            "1",
            "1",
            1.1,
            true
        )

        coEvery { repository.saveMovie(item) } just Runs
        useCase.execute(item)

        coVerify(exactly = 1) { repository.saveMovie(item) }

    }

}