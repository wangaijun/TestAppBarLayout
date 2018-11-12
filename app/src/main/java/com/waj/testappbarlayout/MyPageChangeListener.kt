package com.waj.testappbarlayout

import android.support.v4.view.ViewPager
import android.widget.ImageView

class MyPageChangeListener(val mImageAnimator:ImageAnimator) : ViewPager.OnPageChangeListener{
    var mCurrentPosition:Int = 0
    var mFinalPosition:Int = 0
    var mIsScrolling:Boolean = false

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //以前滑动，现在终止
        if(isFinishedScrolling(position,positionOffset)){
            finishScroll(position)
        }

        //判断前后滑动是否开始
        if (isStartingScrollToPrevious(position,positionOffset)){
            startScroll(position)
        }else if (isStartingScrollToNext(position,positionOffset)){
            startScroll(position+1)
        }

        //向后滑动
        if (isScrollingToNext(position,positionOffset)){
            mImageAnimator.forward(positionOffset)
        }else if (isScrollingToPrevious(position,positionOffset)){//向前滚动
            mImageAnimator.backwards(positionOffset)
        }
    }

    fun isFinishedScrolling(position: Int, positionOffset: Float) =
        mIsScrolling && (positionOffset==0f&&position==mFinalPosition) || !mImageAnimator.isWithin(position)

    fun isStartingScrollToNext(position: Int, positionOffset: Float) =
        !mIsScrolling && position==mCurrentPosition && positionOffset!=0f

    fun isStartingScrollToPrevious(position: Int, positionOffset: Float) =
        !mIsScrolling && position!=mCurrentPosition && positionOffset!=0f

    fun isScrollingToNext(position: Int, positionOffset: Float) =
        mIsScrolling && position==mCurrentPosition && positionOffset!=0f

    fun isScrollingToPrevious(position: Int, positionOffset: Float) =
        mIsScrolling && position!=mCurrentPosition && positionOffset!=0f

    fun startScroll(position: Int) {
        mIsScrolling = true
        mFinalPosition = position
        mImageAnimator.start(mCurrentPosition,position)
    }

    fun finishScroll(position: Int){
        if (mIsScrolling){
            mCurrentPosition = position
            mIsScrolling = false
            mImageAnimator.end(position)
        }
    }

    override fun onPageSelected(p0: Int) {

    }

    companion object {
        fun newInstance(adapter: SimpleAdapter,originImage: ImageView,outgoingImage: ImageView): MyPageChangeListener {
            val imageAnimator = ImageAnimator(adapter,originImage,outgoingImage)
            return MyPageChangeListener(imageAnimator)
        }
    }
}