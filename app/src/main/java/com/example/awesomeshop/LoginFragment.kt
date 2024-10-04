package com.example.awesomeshop

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.awesomeshop.databinding.FragmentLoginBinding
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.LoginViewModel

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

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        viewModel.items.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {

                val token = response.body()?.token
                Toast.makeText(
                    requireContext(),
                    "Login Successful. Token: $token",
                    Toast.LENGTH_SHORT
                ).show()
                val fullName = binding.etFullName.text.toString()
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                sharedPreference.saveCredentials(username, password, fullName)
                navigateToHomeFragment()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Login Failed: ${response.message()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun navigateToHomeFragment() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val fullName = binding.etFullName.text.toString()
        sharedPreference.saveCredentials(username, password, fullName)
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment(fullName)
        findNavController().navigate(action)
    }

    private fun loadSavedCredentials() {
        val savedFullName = sharedPreference.getFullName()
        val savedUsername = sharedPreference.getUsername()
        val savedPassword = sharedPreference.getPassword()
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {

            binding.etFullName.setText(savedFullName)
            binding.etUsername.setText(savedUsername)
            binding.etPassword.setText(savedPassword)
            viewModel.login(savedUsername, savedPassword)
        }
    }
}
