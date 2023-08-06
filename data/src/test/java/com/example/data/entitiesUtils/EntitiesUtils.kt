package com.example.data.entitiesUtils

import com.example.testapplication.data.database.MovieDbModel
import com.example.testapplication.data.network.model.MovieItemDto
import com.example.testapplication.data.network.model.MovieListDto

class EntitiesUtils {

    fun createMovieDbModel(
        id: Int = 1,
        overview: String = "overview test",
        posterPath: String = "http test",
        releaseDate: String = "01-01-2022",
        title: String = "title test",
        voteAverage: Double = 1.1,
        isFavorite: Boolean = false
    ) = MovieDbModel(
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )


    fun createMovieItemDto(
        id: Int = 1,
        overview: String = "overview test",
        posterPath: String = "http test",
        releaseDate: String = "01-01-2022",
        title: String = "title test",
        voteAverage: Double = 1.1,
        isFavorite: Boolean = false
    ) = MovieItemDto(
        id = id,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )

    fun createMovieListDto(
        results: List<MovieItemDto> = listOf(
            MovieItemDto(
                4351,
                "American corporations are using the North American Free Trade Agreement by opening large maquiladoras right across the United States–Mexico border. The maquiladoras hire mostly Mexican women to work long hours for little money in order to produce mass quantity products. Lauren Adrian, an impassioned American news reporter for the Chicago Sentinel wants to be assigned to the Iraq front-lines to cover the war. Instead, her editor George Morgan assigns her to investigate a series of slayings involving young maquiladora factory women in a Mexican bordertown.",
                "/c0AR36VzvB5ileCoMfSlV9bFsVn.jpg",
                "2007-02-15",
                "Bordertown",
                6.3,
                false
            ),
            MovieItemDto(
                1057872,
                "",
                "/hQNZBjUoLVW3WZhIvtK3fD15p4V.jpg",
                "2023-04-21",
                "Tokyo Revengers 2 Part 1: Bloody Halloween - Destiny",
                0.0,
                false
            )
        )
    ) = MovieListDto(
        1,
         results = results,
        10,
        20
    )



}