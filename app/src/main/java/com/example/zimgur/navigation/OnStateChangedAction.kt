package com.example.zimgur.navigation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton


interface OnStateChangedAction {
    fun onStateChanged(sheet: View, newState: Int)
}


class ShowHideFabStateAction(private val fab: FloatingActionButton) : OnStateChangedAction {

    override fun onStateChanged(sheet: View, newState: Int) {
        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
            fab.show()
        } else {
            fab.hide()
        }
    }
}

