package com.waj.testappbarlayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class SimpleFragment : Fragment(){
    /**加载布局文件*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    /**显示数据*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectionNum = arguments?.getString("selectionNum")
        for (i in 0..100){
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\t")
            tv.append("第${selectionNum}页\n")
        }
    }

    companion object {
        /**静态接口封装类的创建，规范Fragment的创建*/
        fun newInstance(selectionNum: String):Fragment {
            val fragment = SimpleFragment()
            val args = Bundle()
            args.putString("selectionNum",selectionNum)
            fragment.arguments = args
            return fragment
        }
    }
}