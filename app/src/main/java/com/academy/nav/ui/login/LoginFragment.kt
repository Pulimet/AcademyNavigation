package com.academy.nav.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.academy.nav.R
import com.academy.nav.databinding.FragmentLoginBinding
import com.academy.nav.ui.binding.FragmentBinding
import com.google.android.material.snackbar.Snackbar

// TODO Step 2 - Please note new fragment was added and it used to simulate user login.
//  This Fragment UI contains only Login button and when user clicks on it we redirect him to the HomeFragment.
class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private val binding by FragmentBinding(FragmentLoginBinding::bind)
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener(this)
    }

    @Suppress("SameParameterValue")
    private fun login(username: String, password: String) {
        loginViewModel.login(username, password).observe(viewLifecycleOwner, { success ->
            if (success) {
                findNavController().popBackStack()
            } else {
                showErrorMessage()
            }
        })
    }

    private fun showErrorMessage() {
        Snackbar.make(requireView(), R.string.error_login_msg, Snackbar.LENGTH_SHORT).show()
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> login("User", "Password")
        }
    }

}