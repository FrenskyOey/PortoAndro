package com.frensky.porto.base.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class CoreFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerFragmentResult()
    }

    open protected fun registerFragmentResult(){
        //please override this function and filing the fragment result on this function
    }

}