package com.example.testapplication.presentation.auth

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentRegistrationBinding
import com.example.testapplication.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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

        emailFocusListener()
        passwordFocusListener()
        repeatPasswordFocusListener()

        binding.btnRegister.setOnClickListener {
            hideKeyBoard()
            registerUser()
        }
    }

    private fun repeatPasswordFocusListener() {
        binding.etPasswordRepeat.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.tilPasswordRepeat.error = checkValidRepeatPassword()
            }
        }
    }

    private fun emailFocusListener() {
        binding.etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.tilEmail.error = checkValidEmail()
            }
        }
    }

    private fun passwordFocusListener() {
        binding.etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.tilPassword.error = checkValidPassword()
            }
        }
    }

    private fun checkValidPassword(): String? {
        val passwordText = binding.etPassword.text.toString()
        if (passwordText.length < 6) {
            return "Password should consist at least 6 characters"
        }
        if (passwordText.isBlank()) {
            return "This is required filed"
        }
        return null
    }

    private fun checkValidEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Check email format"
        }
        if (emailText.isBlank()) {
            return "This is required filed"
        }
        return null
    }

    private fun checkValidRepeatPassword(): String? {
        val repeatPasswordText = binding.etPasswordRepeat.text.toString()
        val passwordText = binding.etPassword.text.toString()
        if (repeatPasswordText.isBlank()) {
            return "This is required filed"
        }
        if (repeatPasswordText != passwordText) {
            return "Passwords don't match"
        }
        return null
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (checkValidEmail() == null && checkValidPassword() == null && checkValidRepeatPassword() == null) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            binding.btnRegister.isEnabled = true
                            showToast(
                                requireContext(),
                                "Account was successfully created"
                            ).also {
                                findNavController().navigateUp()
                            }
                        } else {
                            if (it.exception is FirebaseAuthUserCollisionException) {
                                AlertDialog.Builder(requireContext())
                                    .setTitle("Reset form")
                                    .setMessage("User with this email already exists")
                                    .setPositiveButton("Reset") { _,_ ->
                                        with(binding) {
                                            etEmail.text = null
                                            etPassword.text = null
                                            etPasswordRepeat.text = null

                                            tilEmail.error = null
                                            tilPassword.error = null
                                            tilPasswordRepeat.error = null
                                        }
                                    }.show()
                            } else {
                                Log.e("error: ", it.exception.toString())
                            }
                        }
                    }
                } catch (e: java.lang.Exception) {
                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun hideKeyBoard() {
        val keyBoard = requireActivity()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoard.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken, 0
        )
        requireView().setOnClickListener {
            hideKeyBoard()
            binding.etEmail.clearFocus()
            binding.etPassword.clearFocus()
            binding.etPasswordRepeat.clearFocus()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}