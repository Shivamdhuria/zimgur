package com.example.zimgur.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zimgur.R
import com.example.zimgur.extensions.inflate
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.utils.ImgurImageLoader
import com.example.zimgur.utils.ImgurUtils
import com.google.android.material.chip.Chip
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_gallery_album.view.*


class GalleryAlbumAdapter(private val callback: GalleryAlbumAdapterListener) : ListAdapter<ImgurGalleryAlbum, GalleryAlbumAdapter.UserDateViewHolder>(UserDataAdapterListDiff()) {

    interface GalleryAlbumAdapterListener {

        fun onGalleryLongPressed(galleryAlbum: ImgurGalleryAlbum): Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDateViewHolder = UserDateViewHolder(
            parent.inflate(R.layout.item_gallery_album)
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
            containerView.apply {
                titleTextView.text = userData.title
                userName.text = userData.account_url
                descriptionTextView.text = userData.description
                userData.tags.forEach {
                    val chip = Chip(chipGroup.context)
                    chip.text = it.name
                    chipGroup.addView(chip)
                }
                userData.cover?.isNotBlank()?.let { ImgurImageLoader.loadImageAndCrop(containerView.context, ImgurUtils.coverImageUrlFromId(userData.cover), containerView.coverImage) }
                ImgurImageLoader.loadImageWithCircularCrop(containerView.context, ImgurUtils.avatarImageUrlFromId(userData.account_url), containerView.avatarImageView)
                setOnLongClickListener { callback.onGalleryLongPressed(userData) }
            }
        }
    }
}
