package com.example.zimgur.navigation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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


/**
 * A [Fragment] which acts as a bottom navigation drawer.
 */
class BottomNavDrawerFragment : Fragment(), NavigationAdapter.NavigationAdapterListener {

    private val adapter by lazy(NONE) { NavigationAdapter(this@BottomNavDrawerFragment) }

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) { from(background_container)
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


    fun toggle() {
        when (behavior.state) {
            STATE_HIDDEN -> open()
            STATE_HIDDEN, STATE_HALF_EXPANDED, STATE_EXPANDED, STATE_COLLAPSED -> close()
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