package com.swapcard.feature.home.adapter

import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class HomepagePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    private final var fragmentList: ArrayList<Fragment> = ArrayList()
    private final var fragmentTitleList1: ArrayList<String> = ArrayList()



    // returns which item is selected from arraylist of fragments.
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList1[position]
    }

    // returns the number of items present in arraylist.
    override fun getCount(): Int {
        return fragmentList.size
    }

    // this function adds the fragment and title in 2 separate  arraylist.
    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList1.add(title)
    }
}  