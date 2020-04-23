package com.example.zimgur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView

class MenuBottomSheetDialogFragment(private val menuRes: Int) : BottomSheetDialogFragment() {

    //TODO: Remove the value from the constructor, app will crash when parent activity destroyed.
    private lateinit var navigationView: NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.menu_bottom_sheet_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView.inflateMenu(menuRes)
        navigationView.setNavigationItemSelectedListener {
            dismiss()
            true
        }
    }
}

