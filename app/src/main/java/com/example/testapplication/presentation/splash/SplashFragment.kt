package com.example.testapplication.presentation.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        CoroutineScope(Dispatchers.Main).launch {
            binding.progress.max = 1000
            val value = 1000
            ObjectAnimator.ofInt(binding.progress, "progress", value)
                .setDuration(2000)
                .start()
            delay(2000)

            findNavController().navigate(R.id.action_splashFragment_to_tabsFragment)
        }
    }
}