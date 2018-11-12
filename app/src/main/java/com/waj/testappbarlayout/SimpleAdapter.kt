package com.waj.testappbarlayout

import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SimpleAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return SimpleFragment.newInstance(position.toString())
    }

    override fun getCount(): Int {
        return 3
    }

    @DrawableRes fun getDrawable(endPosition: Int) =
        when(endPosition){
            0->R.drawable.m1
            1->R.drawable.m2
            else->R.drawable.m3
        }

}