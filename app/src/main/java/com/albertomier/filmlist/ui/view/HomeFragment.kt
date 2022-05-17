package com.albertomier.filmlist.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.albertomier.filmlist.databinding.FragmentHomeBinding
import com.albertomier.filmlist.domain.model.Film
import com.albertomier.filmlist.ui.adapter.FilmAdapter
import com.albertomier.filmlist.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ListViewModel by viewModels()

    lateinit var adapter: FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 2)
        binding.filmList.layoutManager = manager

        listViewModel.onCreate()

        listViewModel.listModel.observe(viewLifecycleOwner, Observer {
            if (binding.filmList.adapter != null) {
                (binding.filmList.adapter as FilmAdapter).updateReceiptsList(it)
            } else {
                adapter = FilmAdapter(it, { film -> onFilmSelected(film) }, { film -> addToFavorite(film) })
                binding.filmList.adapter = adapter
            }
        })

        listViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        binding.searcherEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty()) {
                    listViewModel.byQuery(s.toString().lowercase())
                } else {
                    listViewModel.empty()
                }
            }
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
            listViewModel.deleteFavorite(film.id)
        } else {
            listViewModel.addToFavorite(film.id)
        }
    }
}