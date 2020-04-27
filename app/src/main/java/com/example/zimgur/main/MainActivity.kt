package com.example.zimgur.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zimgur.MenuBottomSheetDialogFragment
import com.example.zimgur.R
import com.example.zimgur.base.BaseActivity
import com.example.zimgur.extensions.setSafeOnClickListener
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.navigation.BottomNavDrawerFragment
import com.example.zimgur.navigation.HalfClockwiseRotateSlideAction
import com.example.zimgur.navigation.ShowHideFabStateAction
import com.example.zimgur.preferences.PreferenceManager
import com.example.zimgur.utils.GenericResult
import com.example.zimgur.utils.ThemeManager
import com.example.zimgur.utils.ThemeManager.DARK_MODE
import com.example.zimgur.utils.ThemeManager.LIGHT_MODE
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : BaseActivity(), GalleryAlbumAdapter.GalleryAlbumAdapterListener, Toolbar.OnMenuItemClickListener {

    internal companion object {

        operator fun invoke(context: Context) = Intent(context, MainActivity::class.java)
    }


    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private val adapter by lazy(NONE) { GalleryAlbumAdapter(this) }
    private val viewModel by lazy(NONE) { ViewModelProvider(this, factory).get(MainActivityViewModel::class.java) }
    private val bottomNavDrawer: BottomNavDrawerFragment by lazy(NONE) { supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawerFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var flags = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        //This should be easily be done by setting "android:windowLightStatusBar" item to true in Day Theme and false
        // in night Theme but it seems to be broken for now. :(
        if (!isDarkTheme(this)) {
            flags = flags or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        window.decorView.systemUiVisibility = flags
        setContentView(R.layout.activity_main)
        setUpAdapter()
        initListeners()
        setInsets()

        viewModel.accessTokenStatus.observe(this) {
            when (it) {
                is GenericResult.Progress -> {
                }
                is GenericResult.Success<*> -> {
                    val list = it.value as List<ImgurGalleryAlbum>
                    adapter.submitList(list)
                }
                is GenericResult.GenericError -> {
                }
                is GenericResult.NetworkError -> {
                }
            }
        }

    }

    private fun setInsets() {
//        https://proandroiddev.com/draw-under-status-bar-like-a-pro-db38cfff2870

        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { view, insets ->

            recyclerView.updatePadding(top = insets.systemWindowInsetTop - 1)
            insets
        }

    }

    private fun initListeners() {

        fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setSafeOnClickListener { toggleTheme(isDarkTheme(this@MainActivity)) }
        }
        bottom_app_bar_content_container.setOnClickListener {
            bottomNavDrawer.toggle()
        }

        val fabView = fab
        val image = bottom_app_bar_chevron
        bottomNavDrawer.apply {
            addOnSlideAction(HalfClockwiseRotateSlideAction(image))
            addOnStateChangedAction(ShowHideFabStateAction(fabView))
        }
        bottom_app_bar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
        }
    }

    /**
     * Set this Activity's night mode based on a user's in-app selection.
     */
    private fun toggleTheme(isDark: Boolean): Boolean {
        val mode = when (isDark) {
            true -> LIGHT_MODE
            false -> DARK_MODE
        }
        ThemeManager.applyTheme(mode)
        recreate()
        preferenceManager.saveThemePreference(mode)
        return true
    }

    private fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
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

    override fun onGalleryLongPressed(galleryAlbum: ImgurGalleryAlbum): Boolean {
        MenuBottomSheetDialogFragment(R.menu.album_bottom_sheet_menu).show(supportFragmentManager, null)
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }

}


