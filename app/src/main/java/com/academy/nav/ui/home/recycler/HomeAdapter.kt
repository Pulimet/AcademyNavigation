package com.academy.nav.ui.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.academy.db.model.Movie
import com.academy.nav.R

class HomeAdapter(private val listener: OnMovieClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(v, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieHolder).onBindViewHolder(data[position])
    }

    override fun getItemCount() = data.size

    fun setItems(items: List<Movie>) {
        data = items
        notifyDataSetChanged()
    }
}