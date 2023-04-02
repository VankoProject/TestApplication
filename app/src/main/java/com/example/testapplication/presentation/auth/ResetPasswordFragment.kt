package com.example.testapplication.presentation.auth

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.DialogResetPasswordBinding
import com.example.testapplication.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ResetPasswordFragment : DialogFragment() {

    private var _binding: DialogResetPasswordBinding? = null
    private val binding get() = _binding!!


    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogResetPasswordBinding.inflate(LayoutInflater.from(requireContext()))
        binding.buttonConfirm.setOnClickListener {
            val email = binding.etResetEmail.text.toString()
            if (checkInputEmail()) {
                auth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        showToast(requireContext(), "Email sent")
                        dialog?.cancel()
                    }
                }
            }
        }

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setCancelable(true)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("Password recovery")
            .setMessage("Please, enter your email for sending the password")
            .create()
    }

    private fun checkInputEmail(): Boolean {
        val email = binding.etResetEmail.text.toString()
        if (email.isBlank()) {
            binding.tilResetEmail.error = "This is required field"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilResetEmail.error = "Check email format"
            return false
        }
        return true
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        val TAG: String = ResetPasswordFragment::class.java.simpleName
    }
}