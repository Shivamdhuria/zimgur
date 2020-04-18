package com.example.zimgur.navigation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zimgur.R
import com.example.zimgur.extensions.getColorFromAttr
import com.example.zimgur.extensions.inflate
import com.example.zimgur.extensions.setSafeOnClickListener
import com.example.zimgur.navigation.data.NavMenuItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.nav_menu_item_layout.view.*

class NavigationAdapter(private val listener: NavigationAdapterListener) :
        ListAdapter<NavMenuItem, NavigationAdapter.CallViewHolder>(CallAdapterListDiff()) {

    interface NavigationAdapterListener {
        fun onNavMenuItemClicked(item: NavMenuItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder = CallViewHolder(parent.inflate(R.layout.nav_menu_item_layout))

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        getItem(position)?.let { holder.inflateView(it) }
    }

    private class CallAdapterListDiff : DiffUtil.ItemCallback<NavMenuItem>() {

        override fun areItemsTheSame(oldItem: NavMenuItem, newItem: NavMenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NavMenuItem, newItem: NavMenuItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class CallViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun inflateView(call: NavMenuItem) {
            val context = containerView.context
            containerView.apply {
                nav_item_title.text = call.titleRes
                when (call.checked) {
                    true -> nav_item_title.setTextColor(context.getColorFromAttr(R.attr.colorSecondary))
                    false -> nav_item_title.setTextColor(context.getColorFromAttr(R.attr.colorError))
                }
                setSafeOnClickListener { listener.onNavMenuItemClicked(call) }
            }

        }
    }
}