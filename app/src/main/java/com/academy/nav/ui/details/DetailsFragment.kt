package com.academy.nav.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.academy.db.model.Movie
import com.academy.nav.R
import com.academy.nav.databinding.FragmentDetailsBinding
import com.academy.nav.di.Injector
import com.academy.nav.ui.binding.FragmentBinding
import javax.inject.Inject

class DetailsFragment : Fragment(R.layout.fragment_details), View.OnClickListener {
    @Inject
    internal lateinit var detailsViewModelFactory: DetailsViewModelFactory
    private val viewModel: DetailsViewModel by viewModels { detailsViewModelFactory }

    private var movie: Movie? = null
    // TODO Step 19 - Remove line 23 and uncommented the line below
    //private val args: DetailsFragmentArgs by navArgs()

    private val binding by FragmentBinding(FragmentDetailsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.appComponent.inject(this)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO Step 20 - To get movie id use: args.movie.id
        val movieId = requireArguments().getInt("movieId")
        ViewCompat.setTransitionName(binding.imgMoviePoster, "image_$movieId")

        // TODO Step 21 - Instead of 4 lines below just call for fillMovieData(), without arguments.
        viewModel.getMovie(movieId).observe(viewLifecycleOwner) {
            this.movie = it
            fillMovieData()
        }

        viewModel.getMovieFromFavorites(movieId).observe(viewLifecycleOwner) {
            binding.ivFavorite.setImageResource(getFavoriteResource(it))
        }
        binding.ivFavorite.setOnClickListener(this)
    }

    private fun getFavoriteResource(isInFavorites: Boolean) =
        if (isInFavorites) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border

    private fun fillMovieData() {
        // TODO Step 22 - Use args.movie instead of movie
        movie?.let {
            binding.imgMoviePoster.load(it.posterUrl)
            binding.tvTitle.text = it.getTitleWithYear()
            binding.tvDescription.text = it.overview
            binding.tvRating.text = String.format("Rating: %s", it.vote.toString())
        }
    }

    override fun onClick(v: View?) {
        // TODO Step 23 - Use args.movie instead of movie
        movie?.let { viewModel.onFavoriteImageClick(it) }
    }
}