package com.academy.nav.ui.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.academy.nav.R
import com.academy.nav.databinding.FragmentWelcomeBinding
import com.academy.nav.ui.binding.FragmentBinding

// TODO Step 1 - Please note new fragment was added and it shown when app launched.
//  It contains one button and when user click on it, we redirect him to the HomeFragment.
class WelcomeFragment : Fragment(R.layout.fragment_welcome), View.OnClickListener {
    private val binding by FragmentBinding(FragmentWelcomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGo.setOnClickListener(this)
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v?.id) {
            // TODO Step 15 - Uncomment the line below to allow the navigation to HomeFragment on button click
            //R.id.btnGo -> findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment())
        }
    }
}