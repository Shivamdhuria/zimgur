package com.example.zimgur.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloud.io.base.BaseActivity
import com.example.zimgur.R
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.utils.GenericResult
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : BaseActivity() {

    internal companion object {

        operator fun invoke(context: Context) = Intent(context, MainActivity::class.java)
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private val adapter by lazy(NONE) { GalleryAlbumAdapter() }


    private val viewModel by lazy(NONE) { ViewModelProvider(this, factory).get(MainActivityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()

        viewModel.accessTokenStatus.observe(this) {
            when (it) {

                is GenericResult.Progress -> Log.e("bnbnvn", it.toString())
                is GenericResult.Success<*> -> {
                    val list = it.value as List<ImgurGalleryAlbum>
                    adapter.submitList(list)
                }
                is GenericResult.GenericError -> Log.e("bnbnvn", it.toString())
                is GenericResult.NetworkError -> Log.e("bnbnvn", it.toString())
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpAdapter() {
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPosts()
    }
}