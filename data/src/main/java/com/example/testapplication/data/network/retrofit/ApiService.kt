package com.example.testapplication.data.network.retrofit

import com.example.testapplication.data.network.model.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = "en-US",
        @Query(QUERY_PARAM_PAGE) page: Int = 1
    ): Response<MovieListDto>

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val API_KEY = "b728862be4859f4b752498f3ff892837"
    }
}