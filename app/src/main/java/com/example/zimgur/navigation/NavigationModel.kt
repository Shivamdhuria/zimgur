package com.example.zimgur.navigation

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
                    titleRes = "t/Viral",
                    checked = false
            ),
            NavMenuItem(
                    id = 1,
                    titleRes = "t/Sci-Fo",
                    checked = false
            ),
            NavMenuItem(
                    id = 2,
                    titleRes = "t/Funny",
                    checked = false
            ),
            NavMenuItem(
                    id = 3,
                    titleRes = "t/Cats",
                    checked = false
            ),
            NavMenuItem(
                    id = 4,
                    titleRes = "t/WorkFromHome",
                    checked = false
            ),
            NavMenuItem(
                    id = 5,
                    titleRes = "t/Pizza",
                    checked = false
            ),
            NavMenuItem(
                    id = 5,
                    titleRes = "t/Coffee",
                    checked = false
            ),
            NavMenuItem(
                    id = 6,
                    titleRes = "t/Tattoo",
                    checked = false
            ),
            NavMenuItem(
                    id = 4,
                    titleRes = "t/Books",
                    checked = false
            ),
            NavMenuItem(
                    id = 7,
                    titleRes = "t/Recipes",
                    checked = false
            ),
            NavMenuItem(
                    id = 8,
                    titleRes = "t/Memes",
                    checked = false
            ),
            NavMenuItem(
                    id = 4,
                    titleRes = "t/Creepy",
                    checked = false
            ),
            NavMenuItem(
                    id = 9,
                    titleRes = "t/Mlem",
                    checked = false
            ),
            NavMenuItem(
                    id = 10,
                    titleRes = "t/Comics",
                    checked = false
            ),
            NavMenuItem(
                    id = 11,
                    titleRes = "t/Crafts",
                    checked = false
            ),
            NavMenuItem(
                    id = 12,
                    titleRes = "t/Unmuted",
                    checked = false
            ),
            NavMenuItem(
                    id = 13,
                    titleRes = "t/Funny",
                    checked = false
            ),
            NavMenuItem(
                    id = 14,
                    titleRes = "t/Pizza",
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
        val newList = navigationMenuItems
        _navigationList.value = newList
    }
}