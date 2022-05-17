package com.albertomier.filmlist.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.albertomier.filmlist.databinding.FragmentFavoritesBinding
import com.albertomier.filmlist.databinding.FragmentHomeBinding
import com.albertomier.filmlist.domain.model.Film
import com.albertomier.filmlist.ui.adapter.FilmAdapter
import com.albertomier.filmlist.ui.viewmodel.FavoritesViewModel
import com.albertomier.filmlist.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 2)
        binding.filmList.layoutManager = manager

        favoritesViewModel.onCreate()

        favoritesViewModel.listModel.observe(viewLifecycleOwner, Observer {
            binding.filmList.adapter =
                FilmAdapter(it, { film -> onFilmSelected(film) }, { film -> addToFavorite(film) })
        })

        favoritesViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onFilmSelected(film: Film) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("_id", film.id)

        startActivity(intent)
    }

    private fun addToFavorite(film: Film) {
        if (film.fav) {
            favoritesViewModel.deleteFavorite(film.id)
        } else {
            favoritesViewModel.addToFavorite(film.id)
        }
    }
}