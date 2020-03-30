package com.example.zimgur.main

import androidx.lifecycle.LiveData
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.main.data.ImgurResponse
import com.example.zimgur.utils.GenericResult

interface MainActivityRepository {

    fun fetchGalleryAlbum(
        section: String, sort: String, window: String, page: String,
        showViral: Boolean, mature: Boolean, album_previews: Boolean): LiveData<GenericResult< ImgurResponse<List<ImgurGalleryAlbum>>?>>
}