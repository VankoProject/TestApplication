package com.example.testapplication.presentation.tabs.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentFavoriteBinding
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.presentation.ViewModelFactory
import com.example.testapplication.presentation.tabs.adapters.favoriteadapter.FavAdapter
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModelFactory by lazy {
        ViewModelFactory(requireContext())
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavoriteViewModel::class.java]
    }

    private lateinit var adapter: FavAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        adapter = FavAdapter(object :
            FavAdapter.FavListener { // GKR: use adapter as class parameter to hold view position
            override fun onClickButtonDelete(movieItem: MovieItem) {
                lifecycleScope.launch {
                    viewModel.deleteFavoriteMovie(movieItem)
                }
            }

            override fun onCurrentMoviePosition(movieItem: MovieItem) {
                val movieId = movieItem.id
                val direction = FavoriteFragmentDirections
                    .actionFavoriteFragmentToDetailFragment2(movieId)
                findNavController().navigate(direction)
            }
        })

        binding.rvFavorite.adapter = adapter

        viewModel.movieFavoriteListLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}