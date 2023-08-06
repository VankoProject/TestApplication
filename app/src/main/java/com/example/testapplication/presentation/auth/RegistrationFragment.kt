package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentRegistrationBinding
import com.example.testapplication.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<RegistrationFragmentArgs>()

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegistrationBinding.bind(view)
        binding.etEmail.setText(args.emailArgs).toString()

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (checkAllFields()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful)
                            showToast(
                                requireContext(),
                                "Account was successfully created"
                            ).also {
                                findNavController().navigateUp()
                            }
                        else
                            Log.e("error: ", it.exception.toString())
                    }
                } catch (e: java.lang.Exception) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        with(binding) {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val repeatPassword = etPasswordRepeat.text.toString()
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
            if (etPassword.length() < 6) {
                tilPassword.error = "Password should consist at least 6 characters"
                tilPasswordRepeat.errorIconDrawable = null
                return false
            }
            if (repeatPassword.isBlank()) {
                tilPasswordRepeat.error = "This is required field"
                tilPasswordRepeat.errorIconDrawable = null
                return false
            }
            if (password != repeatPassword) {
                tilPassword.error = "Passwords don't match"
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