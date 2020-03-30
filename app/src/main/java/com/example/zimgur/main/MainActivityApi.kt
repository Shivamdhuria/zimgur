package com.example.zimgur.main

import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.main.data.ImgurResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MainActivityApi {

    @GET("/3/gallery/{section}/{sort}/{window}/{page}")
    suspend fun getGallery(
        @Path("section") section: String, @Path("sort") sort: String, @Path("window") window: String, @Path("page") page: String,
        @Query("showViral") showViral: Boolean, @Query("mature") mature: Boolean, @Query("album_previews") album_previews: Boolean
    ): ImgurResponse<List<ImgurGalleryAlbum>>
}