package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentRegistrationBinding

class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
    }
}