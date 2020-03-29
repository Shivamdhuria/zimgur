package com.example.zimgur.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.zimgur.extensions.safeApiCall
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.main.data.ImgurResponse
import com.example.zimgur.utils.GenericResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class MainActivityRemoteRepository @Inject constructor(private val api: MainActivityApi) : MainActivityRepository {

    override fun fetchGalleryAlbum(section: String, sort: String, window: String, page: String, showViral: Boolean, mature: Boolean,
        album_previews: Boolean): LiveData<GenericResult<ImgurResponse<ImgurGalleryAlbum>?>> = liveData {
        val apiResult = safeApiCall(Dispatchers.IO) {
            Log.e("bjmbmbh", "api result")
            api.getGallery(section, sort, window, page, showViral, mature, album_previews)
        }
        emit(apiResult)
    }
}