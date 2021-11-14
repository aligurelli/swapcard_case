package com.swapcard.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.swapcard.aligurelli.core.network.responses.Artist
import com.swapcard.feature.home.databinding.RowArtistBinding

class ArtistAdapter(var clickListener: (Artist) -> Unit ) :
    PagingDataAdapter<Artist, ArtistAdapter.ArtistViewHolder>(ArtistComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistViewHolder(
            RowArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ArtistViewHolder(private val binding: RowArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Artist) = with(binding) {
            artist = item
            executePendingBindings()
        }
    }

    object ArtistComparator : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem == newItem
    }
}