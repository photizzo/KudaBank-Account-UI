package com.agromall.tellerium

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Shared State Adapter for Fragments.
 */
class FragmentStateAdapter(
    activity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    /**
     * Gets the number of fragments.
     */
    override fun getItemCount(): Int = fragments.size

    /**
     * Gets the fragment in the [position]
     */
    override fun createFragment(position: Int): Fragment = fragments[position]
}