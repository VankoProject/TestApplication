package com.example.testapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    val title: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

)