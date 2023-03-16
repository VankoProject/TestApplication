package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentSignInBinding

class SignInFragment: Fragment(R.layout.fragment_sign_in) {

    lateinit var binding: FragmentSignInBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSignInBinding.bind(view)

    }
}