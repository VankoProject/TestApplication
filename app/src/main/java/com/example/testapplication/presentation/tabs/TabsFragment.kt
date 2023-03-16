package com.example.testapplication.presentation.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentTabsBinding

class TabsFragment: Fragment(R.layout.fragment_tabs) {

    lateinit var binding: FragmentTabsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(
            R.id.tabsFragmentContainer) as NavHostFragment
        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavMenu, navController)
    }
}