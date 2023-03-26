package com.example.testapplication.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentRegistrationBinding

class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null
    private val args by navArgs<RegistrationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)
        binding!!.etEmail.setText(args.emailArgs).toString()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}