package com.example.testapplication.presentation.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

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
            signInUser()
        }

        binding.btnRegistration.setOnClickListener {
            val email = binding.etEmail.text.toString()
            sendEmailToRegistration(email)
        }

        binding.tvForgotPassword.setOnClickListener {
            dialogFragment.show(parentFragmentManager, ResetPasswordFragment.TAG)
        }
    }

    override fun onResume() {
        super.onResume()
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val bottomDown = AnimationUtils.loadAnimation(context, R.anim.bottom_down)

        binding.topLinearLayout.animation = bottomDown

        lifecycleScope.launch {
            delay(1000)
            with(binding) {
                cardView.animation = fadeIn
                tvTitle.animation = fadeIn
                btnFacebook.animation = fadeIn
                btnGoogle.animation = fadeIn
                btnRegistration.animation = fadeIn
            }
        }
    }

    private fun signInUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (checkAllFields()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(TAG, "signInWithEmail: success")
                            findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
                        } else {
                            Log.e("error: ", it.exception.toString())
                            Toast.makeText(
                                requireContext(),
                                "User is not registered. Please create account",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
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