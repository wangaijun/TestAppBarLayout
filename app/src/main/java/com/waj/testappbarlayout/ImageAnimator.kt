package com.waj.testappbarlayout

import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView

/**
 * 显示当前照片：mTargetImage
 * 显示目标照片：mOutgoingImage
 * */
class ImageAnimator(private val mAdapter:SimpleAdapter, private val mTargetImage:ImageView, private val mOutgoingImage:ImageView){
    var mStartPosition:Int = 0 //页面移动前的位置信息，用于更新页面图片
    var mMinPos:Int = 0 //开始位置和结束位置之间的最小值
    var mMaxPos:Int = 0 //开始位置和结束位置之间的最大值

    /**
     * 滑动开始的动画效果
     * */
    fun start(startPosition: Int, endPosition: Int) {
        mStartPosition = startPosition
        mMinPos = Math.min(startPosition,endPosition)
        mMaxPos = Math.max(startPosition,endPosition)

        //设置动画的起始图片
        mOutgoingImage.setImageDrawable(mTargetImage.drawable) //原始图片
        mOutgoingImage.translationX = 0f
        mOutgoingImage.visibility = View.VISIBLE
        mOutgoingImage.alpha = 1.0f

        //设置动画的目标图片
        @DrawableRes val incomeId = mAdapter.getDrawable(endPosition)
        mTargetImage.setImageResource(incomeId)
    }

    /**
     * 滑动结束的动画效果
     * */
    fun end(endPosition: Int) {
        mTargetImage.translationX = 0f

        if (endPosition == mStartPosition){//滑动执行并未完成，返回当前页面
            mTargetImage.setImageDrawable(mOutgoingImage.drawable)
        }else{//滑动执行完成，隐藏动画图片
            @DrawableRes val incomeId = mAdapter.getDrawable(endPosition)
            mTargetImage.setImageResource(incomeId)
            mTargetImage.alpha = 1f
            mOutgoingImage.visibility = View.GONE
        }
    }

    /**
     * 向前滚动，比如0->1, offset滚动的距离，目标渐渐淡出
     * */
    fun forward(positionOffset: Float) {
        val width = mTargetImage.width
        //这个ImageView逐渐往前移
        mOutgoingImage.translationX = -1 * positionOffset * (FACTOR*width)

        mTargetImage.translationX = (1-positionOffset) * (FACTOR*width)
        mTargetImage.alpha = positionOffset
    }

    /**
     * 向后滚动，比如1->0，offset滚动的距离（1->0），目标渐渐淡入
     * */
    fun backwards(positionOffset: Float) {
        val width = mTargetImage.width

        mOutgoingImage.translationX = (1-positionOffset) * (FACTOR*width)

        mTargetImage.translationX = -1 * positionOffset * (FACTOR*width)
        mTargetImage.alpha = 1-positionOffset
    }

    /**
     * 判断位置是否在其中，用于停止动画，用于防止快速滑动导致动画重叠
     * */
    fun isWithin(position: Int):Boolean {
        return position in mMinPos..(mMaxPos - 1)
    }

    companion object {
        const val FACTOR = 0.1F //左右偏移动画的偏移度，部分偏移且偏移范围在10%以内
    }
}