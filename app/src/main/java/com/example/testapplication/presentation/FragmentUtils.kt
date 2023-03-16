package com.example.testapplication.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapplication.presentation.tabs.dashboard.DashBoardViewModel
import com.example.testapplication.presentation.tabs.detail.DetailViewModel
import com.example.testapplication.presentation.tabs.favorite.FavoriteViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashBoardViewModel::class.java)) {
            return DashBoardViewModel(context) as T
        }

        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(context) as T
        }

        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(context) as T
        }

        throw RuntimeException("Unknown view model class $modelClass")

    }
}