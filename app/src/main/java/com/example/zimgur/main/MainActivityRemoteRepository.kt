package com.example.zimgur.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.zimgur.extensions.safeApiCall
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.utils.ApiResponseHandler
import com.example.zimgur.utils.GenericResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class MainActivityRemoteRepository @Inject constructor(private val api: MainActivityApi) : MainActivityRepository {

    override fun fetchGalleryAlbum(
            section: String, sort: String, window: String, page: String, showViral: Boolean, mature: Boolean, album_previews: Boolean
    ): LiveData<GenericResult<*>> = liveData {
        val apiResult = safeApiCall(Dispatchers.IO) {
            api.getGallery(section, sort, window, page, showViral, mature, album_previews)
        }
//        emit(apiResult)
        emit(object : ApiResponseHandler<List<ImgurGalleryAlbum>>(response = apiResult) {

            override suspend fun handleSuccess(resultObj: List<ImgurGalleryAlbum>): GenericResult<*> {
                return GenericResult.Success(resultObj)
            }
        }.getResult()
        )
    }
}