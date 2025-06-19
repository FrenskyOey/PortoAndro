package com.frensky.porto.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class CoreBaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        registerFragmentResult()
        super.onCreate(savedInstanceState)
    }

    open protected fun registerFragmentResult(){
        //please override this function and filing the fragment result on this function
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            try{
                supportFragmentManager.popBackStackImmediate()
            }
            catch(e: Exception){
                e.printStackTrace()
            }
        } else {
            super.onBackPressed()
        }
    }

}