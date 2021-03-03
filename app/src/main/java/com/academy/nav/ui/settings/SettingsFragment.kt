package com.academy.nav.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.academy.nav.R
import com.academy.nav.di.Injector
import javax.inject.Inject
import com.academy.nav.databinding.FragmentSettingsBinding
import com.academy.nav.ui.binding.FragmentBinding

class SettingsFragment : Fragment(R.layout.fragment_settings), View.OnClickListener {
    @Inject
    internal lateinit var settingsViewModelFactory: SettingsViewModelFactory
    private val viewModel: SettingsViewModel by viewModels { settingsViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.getSettingsComponent().inject(this)
    }

    private val binding by FragmentBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.apply {
            setOnClickListenerForViews(btnMinusVotes, btnPlusVotes, btnMinusRating, btnPlusRating)
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            minVotesLiveData.observe(viewLifecycleOwner) {
                binding.tvMinVotesValue.text = it.toString()
            }
            minRatingLiveData.observe(viewLifecycleOwner) {
                binding.tvMinRatingValue.text = it.toString()
            }
        }
    }

    private fun setOnClickListenerForViews(vararg views: View) {
        views.forEach { it.setOnClickListener(this) }
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMinusVotes -> viewModel.onBtnMinusVotesNumClick()
            R.id.btnPlusVotes -> viewModel.onBtnPlusVotesNumClick()
            R.id.btnMinusRating -> viewModel.onBtnMinusRatingClick()
            R.id.btnPlusRating -> viewModel.onBtnPlusRatingClick()
        }
    }
}