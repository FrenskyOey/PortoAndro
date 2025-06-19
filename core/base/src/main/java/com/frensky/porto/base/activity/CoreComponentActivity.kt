package com.frensky.porto.base.activity

import android.os.Bundle
import androidx.activity.ComponentActivity

class CoreComponentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        registerFragmentResult()
        super.onCreate(savedInstanceState)
    }

    open protected fun registerFragmentResult(){
        //please override this function and filing the fragment result on this function
    }


}