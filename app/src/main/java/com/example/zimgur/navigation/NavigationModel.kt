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

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zimgur.navigation.data.NavMenuItem

/**
 * A class which maintains and generates a navigation list to be displayed by [NavigationAdapter].
 */
object NavigationModel {

    private var navigationMenuItems = mutableListOf(
            NavMenuItem(
                    id = 0,
                    titleRes = "R.string.navigation_inbox",
                    checked = false
            ),
            NavMenuItem(
                    id = 1,
                    titleRes = "R.string.navigation_starred",
                    checked = false
            ),
            NavMenuItem(
                    id = 2,
                    titleRes = " R.string.navigation_sent",
                    checked = false
            ),
            NavMenuItem(
                    id = 3,
                    titleRes = "R.string.navigation_trash",
                    checked = false
            ),
            NavMenuItem(
                    id = 4,
                    titleRes = " R.string.navigation_spam",
                    checked = false
            ),
            NavMenuItem(
                    id = 5,
                    titleRes = "",
                    checked = false
            )
    )

    private val _navigationList: MutableLiveData<List<NavMenuItem>> = MutableLiveData()
    val navigationList: LiveData<List<NavMenuItem>> = _navigationList

    init {
        postListUpdate()
    }

    /**
     * Set the currently selected menu item.
     *
     * @return true if the currently selected item has changed.
     */
    fun setNavigationMenuItemChecked(id: Int): Boolean {
        var updated = false
        navigationMenuItems.forEachIndexed { index, item ->
            val shouldCheck = item.id == id
            if (item.checked != shouldCheck) {
                navigationMenuItems[index] = item.copy(checked = shouldCheck)
                updated = true
            }
        }
        if (updated) postListUpdate()
        return updated
    }

    private fun postListUpdate() {
        Log.e("NAV", "updating new list")
        val newList = navigationMenuItems
        _navigationList.value = newList
    }
}

