package br.com.fiap.movies.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.movies.data.MovieModel
import br.com.fiap.movies.databinding.ViewMovieBinding

class MovieViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding = ViewMovieBinding.bind(view)

    fun bind(movie: MovieModel, onMovieClick: (MovieModel) -> Unit) {
        binding.movieTextView.text = movie.name
        binding.movieCard.setOnClickListener {
            onMovieClick.invoke(movie)
        }
    }

}