package com.example.zimgur.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: MainActivityRepository) :
    ViewModel() {

    private val loginInput = MutableLiveData<Int>()
    private val atomicInteger = AtomicInteger()

    internal val accessTokenStatus by lazy {
        loginInput.switchMap {
            repository.fetchGalleryAlbum(
                "hot", "viral", "day", "1",
                showViral = true, mature = true, album_previews = true
            )
        }
    }

    fun fetchPosts() {
        loginInput.value = atomicInteger.incrementAndGet()
    }
}