package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    private val dialogFragment = ResetPasswordFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (checkAllFields()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful)
                        findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
                     else
                        Log.e("error: ", it.exception.toString())
                }
            }
        }

        binding.btnRegistration.setOnClickListener {
            val email = binding.etEmail.text.toString()
            sendEmailToRegistration(email)
        }

        binding.tvForgotPassword.setOnClickListener {
           dialogFragment.show(parentFragmentManager, ResetPasswordFragment.TAG)
        }
    }


    private fun sendEmailToRegistration(email: String) {
        val emailArgs = email.ifBlank { "" }
        val direction =
            SignInFragmentDirections
                .actionSignInFragmentToRegistrationFragment(emailArgs)
        findNavController().navigate(direction)
    }

    private fun checkAllFields(): Boolean {
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (email.isBlank()) {
                tilEmail.error = "This is required filed"
                return false
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Check email format"
                return false
            }
            if (password.isBlank()) {
                tilPassword.error = "This is required field"
                tilPassword.errorIconDrawable = null
                return false
            }
            return true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}