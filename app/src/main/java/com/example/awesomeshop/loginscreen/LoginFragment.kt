package com.example.awesomeshop.loginscreen

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.awesomeshop.R
import com.example.awesomeshop.databinding.FragmentLoginBinding
import com.example.awesomeshop.sharedpreference.SharedPreferenceHelper
import com.example.awesomeshop.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreference: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        sharedPreference = SharedPreferenceHelper(requireContext())
        loadSavedCredentials()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.visibilityOn.setOnClickListener {
            binding.visibilityOn.visibility = View.GONE
            binding.visibilityOff.visibility = View.VISIBLE
            binding.etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }

        binding.visibilityOff.setOnClickListener {
            binding.visibilityOff.visibility = View.GONE
            binding.visibilityOn.visibility = View.VISIBLE
            binding.etPassword.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val fullName = binding.etFullName.text.toString().trim()

            if (validateInputs(username, password, fullName)) {
                viewModel.login(username, password, fullName)
            }
        }

        viewModel.items.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val token = response.body()?.token
                Toast.makeText(requireContext(), "Login Successful. Token: $token", Toast.LENGTH_SHORT).show()

                val fullName = binding.etFullName.text.toString()
                if (token != null) {
                    sharedPreference.saveCredentials(token, fullName)
                }
                navigateToHomeFragment(fullName)

            } else {
                Toast.makeText(requireContext(), "Login Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(username: String, password: String, fullName: String): Boolean {

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.login_failed_error), Toast.LENGTH_SHORT).show()
            return false
        }

        if (fullName.any { it.isDigit() }) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.name_digit_error), Toast.LENGTH_SHORT).show()
            return false
        }

        if (fullName.trim().isEmpty() || fullName.replace(" ", "").isEmpty()) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.name_spacing_error), Toast.LENGTH_SHORT).show()
            return false
        }

        if (fullName.length < 3) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.name_length_error), Toast.LENGTH_SHORT).show()
            return false
        }

        if (username.length < 3) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.user_length_error), Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(requireContext(), requireContext().getString(R.string.password_length_error), Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


    private fun navigateToHomeFragment(fullName: String) {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(fullName)
        findNavController().navigate(action)
    }

    private fun loadSavedCredentials() {
        val token = sharedPreference.getToken()
        val fullName = sharedPreference.getFullName()
        if (token != null) {
            navigateToHomeFragment(fullName!!)
        }

    }
}
