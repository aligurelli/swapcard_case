package com.swapcard.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swapcard.aligurelli.core.network.responses.Artist
import com.swapcard.feature.home.databinding.RowArtistBinding
import com.swarcards.commons.ui.base.BasePagedListAdapter
import com.swarcards.commons.ui.base.BaseViewHolder

class ArtistAdapter(var clickListener: (Artist) -> Unit ) :
    BasePagedListAdapter<Artist>(
        itemsSame = { old, new -> old.id == new.id },
        contentsSame = { old, new -> old == new }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ArtistViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArtistViewHolder) {
            holder.bind(getItem(position))
        }
    }


    inner class ArtistViewHolder(inflater: LayoutInflater) :
        BaseViewHolder<RowArtistBinding>(
            binding = RowArtistBinding.inflate(inflater)
        ) {

        fun bind( item: Artist?) {
            binding.artist = item
            binding.executePendingBindings()

            binding.root.setOnClickListener { item?.let { clickListener.invoke(it) } }
        }
    }

}