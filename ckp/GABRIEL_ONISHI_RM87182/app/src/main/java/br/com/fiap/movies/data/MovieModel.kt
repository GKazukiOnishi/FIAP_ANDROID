package br.com.fiap.movies.data

import java.io.Serializable

data class MovieModel(
    val name: String,
    val synopsis: String,
    val parentalRating: String,
    val genre: String,
    val duration: String,
    val inTheaters: Boolean
) : Serializable
