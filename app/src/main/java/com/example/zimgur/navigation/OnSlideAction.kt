package com.example.zimgur.navigation

import android.view.View
import androidx.annotation.FloatRange
import com.example.zimgur.utils.normalize
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * An action to be performed when a bottom sheet's slide offset is changed.
 */
interface OnSlideAction {
    /**
     * Called when the bottom sheet's [slideOffset] is changed. [slideOffset] will always be a
     * value between -1.0 and 1.0. -1.0 is equal to [BottomSheetBehavior.STATE_HIDDEN], 0.0
     * is equal to [BottomSheetBehavior.STATE_HALF_EXPANDED] and 1.0 is equal to
     * [BottomSheetBehavior.STATE_EXPANDED].
     */
    fun onSlide(
            sheet: View,
            @FloatRange(from = -1.0, fromInclusive = true, to = 1.0, toInclusive = true) slideOffset: Float
    )
}

/**
 * A slide action which rotates a view counterclockwise by 180 degrees between the hidden state
 * and the half expanded state.
 */
class HalfClockwiseRotateSlideAction(private val view: View) : OnSlideAction {

    override fun onSlide(sheet: View, slideOffset: Float) {
        view.rotation = slideOffset.normalize(-1F, 0F, 0F, 180F)
    }
}