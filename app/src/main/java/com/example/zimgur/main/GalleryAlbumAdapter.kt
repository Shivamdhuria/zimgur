package com.example.zimgur.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zimgur.R
import com.example.zimgur.extensions.inflate
import com.example.zimgur.main.data.ImgurGalleryAlbum
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_gallery_album.view.*


class GalleryAlbumAdapter : ListAdapter<ImgurGalleryAlbum, GalleryAlbumAdapter.UserDateViewHolder>(UserDataAdapterListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDateViewHolder = UserDateViewHolder(
        parent.inflate(
            R.layout.item_gallery_album
        )
    )

    override fun onBindViewHolder(holder: UserDateViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class UserDataAdapterListDiff : DiffUtil.ItemCallback<ImgurGalleryAlbum>() {

        override fun areItemsTheSame(oldItem: ImgurGalleryAlbum, newItem: ImgurGalleryAlbum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImgurGalleryAlbum, newItem: ImgurGalleryAlbum): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserDateViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(userData: ImgurGalleryAlbum) {

            containerView.titleTextView.text = userData.title
            containerView.descriptionTextView.text = userData.description
        }
    }
}