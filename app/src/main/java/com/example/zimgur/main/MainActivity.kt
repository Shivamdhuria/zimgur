package com.example.zimgur.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zimgur.MenuBottomSheetDialogFragment
import com.example.zimgur.R
import com.example.zimgur.base.BaseActivity
import com.example.zimgur.extensions.setSafeOnClickListener
import com.example.zimgur.main.data.ImgurGalleryAlbum
import com.example.zimgur.navigation.AlphaSlideAction
import com.example.zimgur.navigation.BottomNavDrawerFragment
import com.example.zimgur.navigation.HalfClockwiseRotateSlideAction
import com.example.zimgur.navigation.ShowHideFabStateAction
import com.example.zimgur.preferences.Credentials
import com.example.zimgur.utils.GenericResult
import com.example.zimgur.utils.ThemeManager
import com.example.zimgur.utils.ThemeManager.DARK_MODE
import com.example.zimgur.utils.ThemeManager.LIGHT_MODE
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
    lateinit var credentials: Credentials

    private val adapter by lazy(NONE) { GalleryAlbumAdapter(this) }
    private val viewModel by lazy(NONE) { ViewModelProvider(this, factory).get(MainActivityViewModel::class.java) }
    private val bottomNavDrawer: BottomNavDrawerFragment by lazy(NONE) { supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavDrawerFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fabB = fab
        setUpAdapter()
        initListeners()


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

    private fun initListeners() {
        fab.setSafeOnClickListener {
//            onDarkThemeMenuItemSelected(isDarkTheme(this))
        }

        fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setSafeOnClickListener { onDarkThemeMenuItemSelected(isDarkTheme(this@MainActivity)) }
        }
        bottom_app_bar_content_container.setOnClickListener {
            bottomNavDrawer.toggle()
        }

        val fabView  = fab
        val image = bottom_app_bar_chevron
        bottomNavDrawer.apply {
            addOnSlideAction(HalfClockwiseRotateSlideAction(image))
//            addOnSlideAction(AlphaSlideAction(binding.bottomAppBarTitle, true))
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
    private fun onDarkThemeMenuItemSelected(isDark: Boolean): Boolean {
        val nightMode = AppCompatDelegate.MODE_NIGHT_YES
//                when (itemId) {
//            R.id.menu_light -> AppCompatDelegate.MODE_NIGHT_NO
//            R.id.menu_dark -> AppCompatDelegate.MODE_NIGHT_YES
//            R.id.menu_battery_saver -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
//            R.id.menu_system_default -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
//            else -> return false
//        }

        val mode = when (isDark) {
            true -> LIGHT_MODE
            false -> DARK_MODE
        }

        ThemeManager.applyTheme(mode)
        credentials.saveThemePreference(mode.toString())
        return true
    }

    fun isDarkTheme(activity: Activity): Boolean {
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

    override fun onEmailLongPressed(galleryAlbum: ImgurGalleryAlbum): Boolean {
        MenuBottomSheetDialogFragment(R.menu.email_bottom_sheet_menu).show(supportFragmentManager, null)
        return true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


