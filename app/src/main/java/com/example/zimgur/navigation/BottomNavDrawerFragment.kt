package com.example.zimgur.navigation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.zimgur.R
import com.example.zimgur.navigation.data.NavMenuItem
import com.example.zimgur.utils.themeColor
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.android.synthetic.main.fragment_bottom_nav_drawer.*
import kotlin.LazyThreadSafetyMode.NONE

class BottomNavDrawerFragment : Fragment(), NavigationAdapter.NavigationAdapterListener {

    private val adapter by lazy(NONE) { NavigationAdapter(this@BottomNavDrawerFragment) }

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(background_container)
    }

    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_nav_drawer, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        background_container.background = backgroundShapeDrawable
        behavior.state = STATE_HIDDEN
        scrim_view.setOnClickListener { close() }
        behavior.addBottomSheetCallback(bottomSheetCallback)
        nav_recycler_view.adapter = adapter
        observeLiveModel()
        NavigationModel.setNavigationMenuItemChecked(0)
        setInsets()
    }

    private fun observeLiveModel() {
        NavigationModel.navigationList.observe(this@BottomNavDrawerFragment) {
            adapter.submitList(it)
        }
    }

    private val backgroundShapeDrawable: MaterialShapeDrawable by lazy(NONE) {
        val backgroundContext = background_container.context
        MaterialShapeDrawable(backgroundContext, null, R.attr.bottomSheetStyle, 0).apply {
            fillColor = ColorStateList.valueOf(backgroundContext.themeColor(R.attr.colorPrimarySurface))

        }
    }

    private fun setInsets() {
//        https://proandroiddev.com/draw-under-status-bar-like-a-pro-db38cfff2870
//
        ViewCompat.setOnApplyWindowInsetsListener(nav_recycler_view) { view, insets ->

            nav_recycler_view.updatePadding(top = insets.systemWindowInsetTop + 10)
            insets
        }

    }


    fun toggle() {
        when {
            behavior.state == STATE_HIDDEN -> open()
            behavior.state == STATE_HIDDEN || behavior.state == STATE_HALF_EXPANDED || behavior.state == STATE_EXPANDED || behavior.state == STATE_COLLAPSED -> close()
        }
    }

    private fun open() {
        behavior.state = STATE_HALF_EXPANDED
    }

    private fun close() {
        behavior.state = STATE_HIDDEN
    }

    fun addOnSlideAction(action: OnSlideAction) {
        bottomSheetCallback.addOnSlideAction(action)
    }

    fun addOnStateChangedAction(action: OnStateChangedAction) {
        bottomSheetCallback.addOnStateChangedAction(action)
    }

    override fun onNavMenuItemClicked(item: NavMenuItem) {
        if (NavigationModel.setNavigationMenuItemChecked(item.id)) {
            adapter.notifyDataSetChanged()
            close()
        }
    }
}