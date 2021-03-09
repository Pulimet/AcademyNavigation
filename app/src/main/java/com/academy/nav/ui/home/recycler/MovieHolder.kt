package com.academy.nav.ui.home.recycler

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.academy.db.model.Movie
import com.academy.nav.databinding.ItemMovieBinding

class MovieHolder(v: View, private val listener: OnMovieClickListener) :
    RecyclerView.ViewHolder(v), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    private val binding = ItemMovieBinding.bind(v)
    private var movie: Movie? = null

    fun onBindViewHolder(movie: Movie) {
        this.movie = movie
        setVotes(movie)
        loadImage(movie)
        ViewCompat.setTransitionName(binding.imgMovie, "image_${movie.id}")
    }

    private fun setVotes(movie: Movie) {
        val votesText = "${movie.vote} (${movie.voteCount})"
        binding.tvVotes.apply {
            visibility = View.GONE
            text = votesText
        }
    }

    private fun loadImage(movie: Movie) {
        movie.posterUrl?.let {
            binding.imgMovie.load(it) {
                crossfade(true)
                scale(Scale.FILL)
                listener(onSuccess = { _, _ ->
                    binding.tvVotes.visibility = View.VISIBLE
                })
            }
        }
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            movie?.let { listener.onClick(it, adapterPosition) }
        }
    }
}