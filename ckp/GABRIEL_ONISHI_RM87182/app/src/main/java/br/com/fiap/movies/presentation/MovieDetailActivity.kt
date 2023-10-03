package br.com.fiap.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.movies.R
import br.com.fiap.movies.data.MovieModel
import br.com.fiap.movies.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val movie by lazy {
        intent.extras?.getSerializable(MOVIE_MODEL_KEY) as MovieModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivityDetails()
    }

    private fun setupActivityDetails() {
        binding.movieDetailMovieName.text = movie.name
        binding.movieDetailInTheatersValue.text = if (movie.inTheaters) getString(R.string.movie_detail_in_theaters_label) else getString(R.string.movie_detail_not_in_theaters_label)
        binding.movieDetailParentalRatingValue.text = if (movie.parentalRating == "Livre") movie.parentalRating else movie.parentalRating + " " + getString(R.string.movie_detail_parental_rating_label_complement)
        binding.movieDetailGenreValue.text = movie.genre
        binding.movieDetailDurationValue.text = movie.duration
        binding.movieDetailSynopsisValue.text = movie.synopsis
    }

    companion object {
        const val MOVIE_MODEL_KEY = "MOVIE_MODEL_KEY"
    }

}