

package com.swapcard.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swapcard.aligurelli.core.database.artistbookmark.BookmarkedArtist
import com.swapcard.feature.home.databinding.RowBookmarkedArtistBinding
import com.swarcards.commons.ui.base.BaseListAdapter
import com.swarcards.commons.ui.base.BaseViewHolder


class BookmarkedArtistsAdapter : BaseListAdapter<BookmarkedArtist>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int) =
        BookmarkedArtistViewHolder(inflater)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookmarkedArtistViewHolder -> holder.bind(getItem(position))
        }
    }


    inner  class BookmarkedArtistViewHolder(
        inflater: LayoutInflater
    ) : BaseViewHolder<RowBookmarkedArtistBinding>(
        binding = RowBookmarkedArtistBinding.inflate(inflater)
    ) {
        fun bind(bookmarkedArtist: BookmarkedArtist) {
            binding.bookMarkedArtist = bookmarkedArtist
            binding.executePendingBindings()
        }
    }
}
