package com.academy.nav.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.academy.nav.R
import com.academy.nav.databinding.FragmentLoginBinding
import com.academy.nav.ui.binding.FragmentBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {
    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }

    private val binding by FragmentBinding(FragmentLoginBinding::bind)

    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        binding.btnLogin.setOnClickListener(this)
    }

    @Suppress("SameParameterValue")
    private fun login(username: String, password: String) {
        loginViewModel.login(username, password).observe(viewLifecycleOwner, { success ->
            if (success) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
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