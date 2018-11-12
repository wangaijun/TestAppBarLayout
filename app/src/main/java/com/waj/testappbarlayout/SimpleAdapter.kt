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

    //为联动的tablayout提供标签名
    override fun getPageTitle(position: Int): CharSequence? = "第${position}页"

    //非必须，为顶部的动画提供响应的图片
    //@DrawableRes,强制编译器在编译阶段检查所传递的int值是否为图片资源，是否不靠谱
    @DrawableRes fun getDrawable(endPosition: Int) =
        when(endPosition){
            0->R.drawable.m1
            1->R.drawable.m2
            else->R.drawable.m3
        }
}