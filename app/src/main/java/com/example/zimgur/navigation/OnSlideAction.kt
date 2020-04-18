package com.example.zimgur.navigation

import android.view.View
import androidx.annotation.FloatRange
import com.example.zimgur.utils.normalize
import com.google.android.material.bottomsheet.BottomSheetBehavior


interface OnSlideAction {

    fun onSlide(
            sheet: View,
            @FloatRange(from = -1.0, fromInclusive = true, to = 1.0, toInclusive = true) slideOffset: Float
    )
}


class HalfClockwiseRotateSlideAction(private val view: View) : OnSlideAction {

    override fun onSlide(sheet: View, slideOffset: Float) {
        view.rotation = slideOffset.normalize(-1F, 0F, 0F, 180F)
    }
}