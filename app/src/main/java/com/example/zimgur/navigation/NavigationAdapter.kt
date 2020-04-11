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
                Log.e("new list...........", call.toString())
                nav_item_title.text = call.titleRes
                if (call.checked) {
                    nav_item_title.setTextColor(context.getColorFromAttr(R.attr.colorSecondary))
                } else {
                    nav_item_title.setTextColor(context.getColorFromAttr(R.attr.colorError))
                }
//                nav_item_title.isSelected = call.checked

                setSafeOnClickListener { listener.onNavMenuItemClicked(call) }
            }

        }
    }
}