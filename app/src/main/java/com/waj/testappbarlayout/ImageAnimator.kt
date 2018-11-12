package com.waj.testappbarlayout

import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView

class ImageAnimator(val mAdapter:SimpleAdapter,val mTargetImage:ImageView,val mOutgoingImage:ImageView){
    var mStartPosition:Int = 0
    var mMinPos:Int = 0
    var mMaxPos:Int = 0

    /**
     * 启动动画，之后选择向前或向后滑动
     * */
    fun start(startPosition: Int, endPosition: Int) {
        mStartPosition = startPosition
        mMinPos = Math.min(startPosition,endPosition)
        mMaxPos = Math.max(startPosition,endPosition)

        //设置动画的起始图片
        mOutgoingImage.setImageDrawable(mTargetImage.drawable) //原始图片
        mOutgoingImage.translationX = 0f
        mOutgoingImage.visibility = View.INVISIBLE
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
     * 判断位置是否在其中，用于停止动画
     * */
    fun isWithin(position: Int):Boolean {
        return position in mMinPos..(mMaxPos - 1)
    }

    companion object {
        const val FACTOR = 0.1F
    }
}