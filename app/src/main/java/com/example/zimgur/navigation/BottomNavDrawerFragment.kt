/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.google.android.material.shape.MaterialShapeDrawable
import kotlinx.android.synthetic.main.fragment_bottom_nav_drawer.*
import kotlin.LazyThreadSafetyMode.NONE


/**
 * A [Fragment] which acts as a bottom navigation drawer.
 */
class BottomNavDrawerFragment : Fragment(), NavigationAdapter.NavigationAdapterListener {

    private val adapter by lazy(NONE) { NavigationAdapter(this@BottomNavDrawerFragment) }

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        BottomSheetBehavior.from(background_container)
    }

    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_nav_drawer, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        background_container.background = backgroundShapeDrawable
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
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
        MaterialShapeDrawable(
                backgroundContext,
                null,
                R.attr.bottomSheetStyle,
                0
        ).apply {
            fillColor = ColorStateList.valueOf(
                    backgroundContext.themeColor(R.attr.colorPrimarySurface)
            )
//            elevation = resources.getDimension(R.dimen.plane_08)
            initializeElevationOverlay(requireContext())
        }
    }


    fun toggle() {
        when {
            behavior.state == BottomSheetBehavior.STATE_HIDDEN -> open()
            behavior.state == BottomSheetBehavior.STATE_HIDDEN
                    || behavior.state == BottomSheetBehavior.STATE_HALF_EXPANDED
                    || behavior.state == BottomSheetBehavior.STATE_EXPANDED
                    || behavior.state == BottomSheetBehavior.STATE_COLLAPSED -> close()
        }
    }

    fun open() {
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    fun close() {
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
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