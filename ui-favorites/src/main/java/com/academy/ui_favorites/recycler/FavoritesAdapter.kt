package com.academy.ui_favorites.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.ui_favorites.R

class FavoritesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<MovieFavorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).onBindViewHolder(data[position])
    }

    override fun getItemCount() = data.size

    fun setItems(items: List<MovieFavorite>) {
        data = items
        notifyDataSetChanged()
    }
}