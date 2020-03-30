package com.example.zimgur.main.data

import com.google.gson.annotations.SerializedName

data class ImgurGalleryAlbum (

        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("datetime")
        val datetime: Long,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("cover_width")
        val cover_width: Int,
        @SerializedName("cover_height")
        val cover_height: Int,
        @SerializedName("account_url")
        val account_url: String,
        @SerializedName("account_id")
        val account_id: Int,
        @SerializedName("privacy")
        val privacy: String,
        @SerializedName("layout")
        val layout: String,
        @SerializedName("views")
        val views: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("ups")
        val ups: Int,
        @SerializedName("downs")
        val downs: Int,
        @SerializedName("points")
        val points: Long,
        @SerializedName("score")
        val score: Long,
        @SerializedName("is_album")
        val is_album: Boolean,
        @SerializedName("vote")
        val vote: String,
        @SerializedName("favorite")
        val favorite: Boolean,
        @SerializedName("nsfw")
        val nsfw: Boolean,
        @SerializedName("comment_count")
        val comment_count: Int,
        @SerializedName("topic")
        val topic: String,
        @SerializedName("topic_id")
        val topic_id: Int,
        @SerializedName("images_count")
        val images_count: Int,
        @SerializedName("images")
        val images: Array<ImgurImage>
)