package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
        }

        binding.btnRegistration.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val emailArgs = email.ifBlank { null }
            val direction =
                SignInFragmentDirections.actionSignInFragmentToRegistrationFragment(emailArgs.toString())
            findNavController().navigate(direction)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}