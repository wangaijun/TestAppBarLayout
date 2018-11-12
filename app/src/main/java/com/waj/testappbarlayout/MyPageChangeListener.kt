package com.waj.testappbarlayout

import android.support.v4.view.ViewPager
import android.widget.ImageView

class MyPageChangeListener(private val mImageAnimator:ImageAnimator) : ViewPager.OnPageChangeListener{
    private var mCurrentPosition:Int = 0
    private var mFinalPosition:Int = 0
    private var mIsScrolling:Boolean = false

    override fun onPageScrollStateChanged(state: Int) {

    }

    /**
     * position参数表示滑动开始的位置
     * positionOffset表示滑动偏移的距离
     * positionOffsetPixels 滑动偏移的像素
     * */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //如果正在执行动画，则终止动画 ，防止动画效果重叠
        if(isFinishedScrolling(position,positionOffset)){
            finishScroll(position)
        }

        //判断页面是移动到下一个位置还是前一个位置，根据移动的位置对动画的两个图片控件ImageView进行初始化
        if (isStartingScrollToPrevious(position,positionOffset)){
            startScroll(position)
        }else if (isStartingScrollToNext(position,positionOffset)){
            startScroll(position+1)
        }

        //判断页面是向前移动还是向后移动，根据移动的偏移执行动画管理器ImageAnimator对应的动画效果
        if (isScrollingToNext(position,positionOffset)){
            mImageAnimator.forward(positionOffset)
        }else if (isScrollingToPrevious(position,positionOffset)){
            mImageAnimator.backwards(positionOffset)
        }
    }

    private fun isFinishedScrolling(position: Int, positionOffset: Float) =
        mIsScrolling && (positionOffset==0f&&position==mFinalPosition) || !mImageAnimator.isWithin(position)

    private fun isStartingScrollToNext(position: Int, positionOffset: Float) =
        !mIsScrolling && position==mCurrentPosition && positionOffset!=0f

    private fun isStartingScrollToPrevious(position: Int, positionOffset: Float) =
        !mIsScrolling && position!=mCurrentPosition && positionOffset!=0f

    private fun isScrollingToNext(position: Int, positionOffset: Float) =
        mIsScrolling && position==mCurrentPosition && positionOffset!=0f

    private fun isScrollingToPrevious(position: Int, positionOffset: Float) =
        mIsScrolling && position!=mCurrentPosition && positionOffset!=0f

    private fun startScroll(position: Int) {
        mIsScrolling = true
        mFinalPosition = position
        mImageAnimator.start(mCurrentPosition,position)
    }

    private fun finishScroll(position: Int){
        if (mIsScrolling){
            mCurrentPosition = position
            mIsScrolling = false
            mImageAnimator.end(position)
        }
    }

    override fun onPageSelected(position: Int) {

    }

    companion object {
        fun newInstance(adapter: SimpleAdapter,originImage: ImageView,outgoingImage: ImageView): MyPageChangeListener {
            val imageAnimator = ImageAnimator(adapter,originImage,outgoingImage)
            return MyPageChangeListener(imageAnimator)
        }
    }
}