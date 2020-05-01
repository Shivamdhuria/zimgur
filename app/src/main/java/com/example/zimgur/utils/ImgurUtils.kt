package com.example.zimgur.utils

object ImgurUtils {
    /**
     * Takes in cover image id and returns imgur url for the image
     */
    fun coverImageUrlFromId(id: String) = "https://i.imgur.com/$id.jpg"

    fun avatarImageUrlFromId(accountId: String) = "https://imgur.com/user/$accountId/avatar"
}