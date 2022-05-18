package com.albertomier.filmlist.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.albertomier.filmlist.R
import com.albertomier.filmlist.databinding.ActivityDetailBinding
import com.albertomier.filmlist.domain.model.Film
import com.albertomier.filmlist.ui.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null && intent.hasExtra("_id")) {
            id = intent.getIntExtra("_id", -1)
        }

        if (id >= 0) {
            detailViewModel.onCreate(id)
        }

        detailViewModel.dataModel.observe(this, Observer {
            if (it == null) {
                Toast.makeText(this@DetailActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                onBackPressed()
            } else {
                val name = it.title
                val path: String = "https://image.tmdb.org/t/p/w500/${it.posterPath}"
                val placeholder =
                    ResourcesCompat.getDrawable(resources, R.mipmap.placeholder_image, null)

                supportActionBar?.title = name

                binding.filmTitle.text = name
                binding.voteAverage.text = "${it.voteAverage}"
                binding.filmDescription.text = it.overview

                when {
                    it.voteAverage >= 8 -> {
                        binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_green)
                    }
                    it.voteAverage >= 6 -> {
                        binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_yellow)
                    }
                    it.voteAverage >= 5 -> {
                        binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_orange)
                    }
                    else -> {
                        binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_red)
                    }
                }

                if (it.fav) {
                    binding.addToFavorite.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this@DetailActivity,
                            R.drawable.ic_star
                        )
                    )
                } else {
                    binding.addToFavorite.setImageDrawable(
                        AppCompatResources.getDrawable(
                            this@DetailActivity,
                            R.drawable.ic_empty_star
                        )
                    )
                }

                Picasso.get().load(path).centerCrop().placeholder(placeholder!!).fit()
                    .into(binding.filmImage)

                binding.addToFavorite.setOnClickListener { _ ->
                    addToFavorite(it)
                }
            }
        })
    }

    private fun addToFavorite(film: Film) {
        if (film.fav) {
            detailViewModel.deleteFavorite(film.id)
        } else {
            detailViewModel.addToFavorite(film.id)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}