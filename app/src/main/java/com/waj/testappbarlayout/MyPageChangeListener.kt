package com.waj.testappbarlayout

import android.support.v4.view.ViewPager

class MyPageChangeListener : ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {

    }

    companion object {
        fun newInstance(): MyPageChangeListener {
            return MyPageChangeListener()
        }
    }
}