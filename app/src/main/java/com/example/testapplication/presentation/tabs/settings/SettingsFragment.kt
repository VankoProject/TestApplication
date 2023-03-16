package com.example.testapplication.presentation.tabs.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }


}