package br.com.fiap.movies.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.movies.data.MovieDataSource
import br.com.fiap.movies.data.MovieModel
import br.com.fiap.movies.databinding.ActivityMovieBinding
import br.com.fiap.movies.presentation.adapter.MovieAdapter

class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.movieRecyclerView.adapter = MovieAdapter(
            MovieDataSource.movieList,
            ::goToMovieDetailActivity
        )
    }

    private fun goToMovieDetailActivity(movie: MovieModel) {
        startActivity(
            Intent(
                this,
                MovieDetailActivity::class.java
            ).putExtra(MovieDetailActivity.MOVIE_MODEL_KEY, movie)
        )
    }

}