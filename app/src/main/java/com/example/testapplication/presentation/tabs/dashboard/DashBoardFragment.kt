package com.example.testapplication.presentation.tabs.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDashBoardBinding
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.presentation.ViewModelFactory
import com.example.testapplication.presentation.tabs.adapters.mainadapter.Listener
import com.example.testapplication.presentation.tabs.adapters.mainadapter.MainAdapter
import com.example.testapplication.presentation.tabs.favorite.FavoriteViewModel
import kotlinx.coroutines.launch


class DashBoardFragment : Fragment(R.layout.fragment_dash_board) {

    private var _binding: FragmentDashBoardBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy {
        ViewModelFactory(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DashBoardViewModel::class.java]
    }
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashBoardBinding.inflate(layoutInflater, container, false)
        adapter = MainAdapter(object : Listener {
            override fun onMovieClick(movieItem: MovieItem) {
                findNavController().navigate(R.id.action_dashBoardFragment_to_detailFragment)
            }

            override fun onIconClick(movieItem: MovieItem) {
                lifecycleScope.launch {
                    viewModel.saveToFavoriteList(movieItem)
                    Log.d("AddMovie", "$movieItem")
                }
            }
        })

        binding.rvMoviesList.adapter = adapter
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)

        viewModel.getPopularMovies().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}