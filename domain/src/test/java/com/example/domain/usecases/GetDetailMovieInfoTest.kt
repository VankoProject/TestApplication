package com.example.domain.usecases

import com.example.domain.MovieItem
import com.example.domain.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetDetailMovieInfoTest {

    @Test
    fun `getDetailInfoMovie() method should returns movieItem by id from database`() = runBlocking {

        val repository = mockk<MovieRepository>(relaxed = true)
        val useCase = GetDetailMovieInfoUseCase(repository)
        val item = MovieItem(
            1,
            "1",
            "1",
            "1",
            "1",
            1.1,
            true
        )

        coEvery { repository.getDetailMovieInfo(item.id) } returns item
        val actual = useCase.execute(item.id)

        Assert.assertEquals(item, actual)
    }
}