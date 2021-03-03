package com.academy.ui_favorites

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.ui_favorites.binding.FragmentBinding
import com.academy.ui_favorites.databinding.FragmentFavoritesBinding
import com.academy.ui_favorites.di.FavoritesComponent
import com.academy.ui_favorites.recycler.FavoritesAdapter
import javax.inject.Inject

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    @Inject
    internal lateinit var favoritesViewModelFactory: FavoritesViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels { favoritesViewModelFactory }
    private val binding by FragmentBinding(FragmentFavoritesBinding::bind)

    private var favoritesAdapter: FavoritesAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoritesComponent.getFavoriteComponent().inject(this)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        observeViewModel()
    }

    private fun setRecyclerView() {
        Log.w("Academy", "setRecyclerView")
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2).apply { gridLayoutManager = this }
            adapter = FavoritesAdapter().apply { favoritesAdapter = this }
        }
    }

    private fun observeViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            favoritesAdapter?.setItems(it)
        }
    }

}