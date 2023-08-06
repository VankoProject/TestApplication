package com.example.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.domain.MovieItem
import com.example.domain.MovieRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class GetFavoriteMoviesUseCaseTest {

    @get:Rule
    val rule = MockKRule(this)

    @RelaxedMockK
    lateinit var repository: MovieRepository
    private val movieList = listOf(
        MovieItem(
            1,
            "overView1",
            "url1",
            "2022-12-12",
            "title1",
            1.1,
            true
        ),
        MovieItem(
            2,
            "overView2",
            "url2",
            "2022-12-11",
            "title2",
            2.1,
            false
        )
    )

    @InjectMockKs
    lateinit var getFavoriteMovieUseCase: GetFavoriteMoviesUseCase


    @Test
    fun `should return the same data as in repository`() {
        //arrange
        every { repository.getFavoriteMovies() } returns MutableLiveData(movieList)
        //act
        val result = getFavoriteMovieUseCase.execute()
        //assert
        assertEquals(movieList, result.value)
    }



}