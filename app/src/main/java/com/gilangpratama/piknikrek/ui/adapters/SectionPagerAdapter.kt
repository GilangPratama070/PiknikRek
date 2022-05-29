package com.gilangpratama.piknikrek.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gilangpratama.piknikrek.ui.onboard.ItemFragment

class SectionPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ItemFragment()
            1 -> fragment = ItemFragment()
            2 -> fragment = ItemFragment()
            3 -> fragment = ItemFragment()
        }
        fragment?.arguments = Bundle().apply {
            putInt(ItemFragment.ARG_SECTION_POSITION, position)
        }
        return fragment as Fragment
    }
}