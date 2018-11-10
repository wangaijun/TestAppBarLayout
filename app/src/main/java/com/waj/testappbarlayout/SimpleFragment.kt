package com.waj.testappbarlayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class SimpleFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv.text = arguments?.getString("selectionNum")
    }

    companion object {
        fun newInstance(selectionNum: String):Fragment {
            val fragment = SimpleFragment()
            val args = Bundle()
            args.putString("selectionNum",selectionNum)
            fragment.arguments = args
            return fragment
        }
    }
}