package com.albertomier.filmlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.filmlist.R
import com.albertomier.filmlist.domain.model.Film

class FilmAdapter(
    private var filmList: List<Film>,
    private val onClickListener: (Film) -> Unit,
    private val addToFavoriteListener: (Film) -> Unit
) : RecyclerView.Adapter<FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmViewHolder(layoutInflater.inflate(R.layout.film, parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val item = filmList[position]
        holder.render(item, onClickListener, addToFavoriteListener)
    }

    override fun getItemCount(): Int {
        if (!filmList.isNullOrEmpty()) {
            return filmList.size
        }
        return 0
    }

    fun updateReceiptsList(newlist: List<Film>) {
        filmList = emptyList()
        filmList = newlist
        notifyDataSetChanged()
    }
}