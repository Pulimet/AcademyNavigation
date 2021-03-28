package com.academy.nav.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.db.model.Movie
import com.academy.nav.R
import com.academy.nav.databinding.FragmentHomeBinding
import com.academy.nav.di.Injector
import com.academy.nav.ui.binding.FragmentBinding
import com.academy.nav.ui.home.recycler.HomeAdapter
import com.academy.nav.ui.home.recycler.OnMovieClickListener
import com.academy.nav.ui.login.LoginViewModel
import com.academy.nav.ui.navigation.NavigationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home), OnMovieClickListener {
    @Inject
    internal lateinit var homeViewModelFactory: HomeViewModelFactory
    private val viewModel: HomeViewModel by viewModels { homeViewModelFactory }

    private val navViewModel: NavigationViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    private val binding by FragmentBinding(FragmentHomeBinding::bind)

    private var homeAdapter: HomeAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.appComponent.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerLoginStatus()
    }

    private fun observerLoginStatus() {
        // TODO Step 14 - Uncomment the lines below to allow the navigation to LoginFragment when user not logged in
        /*loginViewModel.user().observe(viewLifecycleOwner) { isUserExist ->
            if (isUserExist) {*/
        setRecyclerView()
        setSwipeRefreshLayout()
        observeViewModel()
        /*} else {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }*/
    }

    private fun setRecyclerView() {
        Log.w("Academy", "setRecyclerView")
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2).apply { gridLayoutManager = this }
            adapter = HomeAdapter(this@HomeFragment).apply { homeAdapter = this }

            // Solves return transition animation
            postponeEnterTransition()
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.onUserRefreshedMain() }
    }

    private fun observeViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            homeAdapter?.setItems(it)
            // Scrolls to position of selected item on going back to the list
            scrollToPreviouslyClickedItem(gridLayoutManager)
            if (it.isNotEmpty()) binding.swipeRefreshLayout.isRefreshing = false
        }


    }

    private fun scrollToPreviouslyClickedItem(layoutManager: RecyclerView.LayoutManager?) {
        lifecycleScope.launch {
            if (viewModel.savedItemPosition > 4) {
                delay(80) // Without this delay scrollToPosition function not working
                layoutManager?.scrollToPosition(viewModel.savedItemPosition)
                delay(10)
                startPostponedEnterTransition()
            } else {
                startPostponedEnterTransition()
            }
        }
    }

    // OnMovieClickListener
    override fun onClick(movie: Movie, extras: FragmentNavigator.Extras, position: Int) {
        viewModel.saveClickedItemPosition(position)
        navViewModel.onUserMovieClick(movie, extras)
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_settings -> {
                viewModel.saveFirstVisiblePosition(gridLayoutManager?.findFirstVisibleItemPosition())
                navViewModel.onSettingsClick()
                true
            }
            R.id.action_favorites -> {
                navViewModel.onFavoritesClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}
