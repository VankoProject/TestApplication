package com.example.testapplication.presentation.tabs.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by viewModel<DetailViewModel>()

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        toolbar = binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.getDetailInfoMovie(args.movieId).observe(viewLifecycleOwner) {
            with(binding) {
                tvDescription.text = it.overview
                tvScore.text = it.voteAverage.toString()
                tvTitleMovie.text = it.title
                Glide.with(binding.root.context)
                    .load(URL_POSTER + it.posterPath)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(ivPosterMovie)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val URL_POSTER = "https://image.tmdb.org/t/p/w500"
    }


}