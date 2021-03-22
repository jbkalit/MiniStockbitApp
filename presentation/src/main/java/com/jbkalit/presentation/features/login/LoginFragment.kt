package com.jbkalit.presentation.features.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jbkalit.presentation.R
import com.jbkalit.presentation.databinding.FragmentLoginBinding
import com.jbkalit.presentation.features.login.viewmodel.LoginViewModel
import com.jbkalit.presentation.main.MainActivity
import com.jbkalit.presentation.util.Constants.MIN_PASSWORD_LENGTH
import com.jbkalit.presentation.util.StringUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isLoggedIn()) {
            navigateToWatchlist()
        }

        setupListener()
        initObserver()
        setupAction()
    }

    private fun setupListener() {
        with (binding) {
            emailEditText.addTextChangedListener {
                if (!StringUtils.isValidEmailAddress(emailEditText.text.toString())){
                    emailTextInput.error =
                        getString(R.string.email_not_valid)
                }
                else {
                    emailTextInput.error = ""
                }
            }
            viewModel.setEmail(emailEditText.text.toString())
            passwordEditText.addTextChangedListener {
                val password = passwordEditText.text.toString()
                if (password.length < MIN_PASSWORD_LENGTH){
                    passwordTextInput.error =
                        getString(R.string.password_not_valid, MIN_PASSWORD_LENGTH)
                }
                else {
                    passwordTextInput.error = ""
                }
            }
            viewModel.setPassword(passwordEditText.text.toString())
        }
    }


    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.authValidation.collect { value ->
                binding.loginButton.isEnabled = value
                Log.d("BUTTON-", value.toString())
            }
        }
    }

    private fun navigateToWatchlist() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToWatchListFragment()
        if (findNavController().currentDestination?.id == R.id.loginFragment) {
            findNavController().navigate(action)
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            viewModel.login()
            navigateToWatchlist()
        }
    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).hideMenu(true)
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).hideMenu(false)
    }

}
