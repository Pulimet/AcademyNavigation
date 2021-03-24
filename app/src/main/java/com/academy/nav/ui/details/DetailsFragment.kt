package com.academy.nav.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.academy.nav.R
import com.academy.nav.databinding.FragmentDetailsBinding
import com.academy.nav.di.Injector
import com.academy.nav.ui.binding.FragmentBinding
import javax.inject.Inject

class DetailsFragment : Fragment(R.layout.fragment_details), View.OnClickListener {
    @Inject
    internal lateinit var detailsViewModelFactory: DetailsViewModelFactory
    private val viewModel: DetailsViewModel by viewModels { detailsViewModelFactory }

    private val args: DetailsFragmentArgs by navArgs()
    private val binding by FragmentBinding(FragmentDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.appComponent.inject(this)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillMovieData()
        ViewCompat.setTransitionName(binding.imgMoviePoster, "image_${args.movie.id}")

        viewModel.getMovieFromFavorites(args.movie.id).observe(viewLifecycleOwner) {
            binding.ivFavorite.setImageResource(getFavoriteResource(it))
        }
        binding.ivFavorite.setOnClickListener(this)
    }

    private fun getFavoriteResource(isInFavorites: Boolean) =
        if (isInFavorites) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border

    private fun fillMovieData() {
        args.movie.let {
            binding.imgMoviePoster.load(it.posterUrl)
            binding.tvTitle.text = it.getTitleWithYear()
            binding.tvDescription.text = it.overview
            binding.tvRating.text = String.format("Rating: %s", it.vote.toString())
        }
    }

    override fun onClick(v: View?) {
        viewModel.onFavoriteImageClick(args.movie)
    }
}