package com.emrexample.mobile.activity

import android.content.Intent
import android.os.Bundle
import com.emrexample.mobile.R
import com.emrexample.mobile.util.makewindowfullScreen
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makewindowfullScreen(window)
        setContentView(R.layout.activity_main)
    }







    override fun onBackPressed() {
        //            super.onBackPressed();
        startActivity(Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_HOME))
    }
}