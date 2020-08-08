package com.example.zimgur.main

import com.example.zimgur.main.data.ImgurGalleryAlbum
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainActivityApi {

    @GET("/3/gallery/{section}/{sort}/{window}/{page}")
    suspend fun getGallery(
            @Path("section") section: String, @Path("sort") sort: String, @Path("window") window: String, @Path("page") page: String,
            @Query("showViral") showViral: Boolean, @Query("mature") mature: Boolean, @Query("album_previews") album_previews: Boolean
    ): List<ImgurGalleryAlbum>
}