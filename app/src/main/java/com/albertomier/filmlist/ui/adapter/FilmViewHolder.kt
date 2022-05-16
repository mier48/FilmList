package com.albertomier.filmlist.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.filmlist.R
import com.albertomier.filmlist.databinding.FilmBinding
import com.albertomier.filmlist.domain.model.Film
import com.squareup.picasso.Picasso

class FilmViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FilmBinding.bind(view)

    fun render(film: Film, onClickListener: (Film) -> Unit) {
        val context: Context = binding.filmImage.context
        val path: String = "https://image.tmdb.org/t/p/w500/${film.posterPath}"
        val placeholder =
            ResourcesCompat.getDrawable(context.resources, R.mipmap.placeholder_image, null)

        binding.filmTitle.text = film.title
        binding.voteAverage.text = "${film.voteAverage}"
        binding.filmDescription.text = film.overview.take(50)

        when {
            film.voteAverage >= 8 -> {
                binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_green)
            }
            film.voteAverage >= 6 -> {
                binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_yellow)
            }
            film.voteAverage >= 5 -> {
                binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_orange)
            }
            else -> {
                binding.voteAverageLayout.setBackgroundResource(R.drawable.circle_red)
            }
        }

        Picasso.get().load(path).centerCrop().placeholder(placeholder!!).fit()
            .into(binding.filmImage)

        itemView.setOnClickListener {
            onClickListener(film)
        }
    }
}