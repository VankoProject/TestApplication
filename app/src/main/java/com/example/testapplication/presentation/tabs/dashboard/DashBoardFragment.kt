package com.example.testapplication.presentation.tabs.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDashBoardBinding
import com.example.testapplication.domain.MovieItem
import com.example.testapplication.presentation.tabs.adapters.mainadapter.Listener
import com.example.testapplication.presentation.tabs.adapters.mainadapter.MainAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashBoardFragment : Fragment(R.layout.fragment_dash_board) {

    private var _binding: FragmentDashBoardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DashBoardViewModel>()

    private val adapter = MainAdapter(object : Listener {
        override fun onMovieClick(movieItem: MovieItem) {
            val movieId = movieItem.id
            val direction = DashBoardFragmentDirections
                .actionDashBoardFragmentToDetailFragment(movieId)
            findNavController().navigate(direction)
        }

        override fun onIconClick(isfavorite: Boolean, movieItem: MovieItem) {
            lifecycleScope.launch {
                if(!isfavorite){
                    viewModel.saveToFavoriteList(movieItem)
                } else {
                    viewModel.deleteFavoriteFilm(movieItem)
                }
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashBoardBinding.inflate(layoutInflater, container, false)

        binding.rvMoviesList.adapter = adapter
        binding.rvMoviesList.layoutManager = GridLayoutManager(context, 2)


        viewModel.movieListLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}