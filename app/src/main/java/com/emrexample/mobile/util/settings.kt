package com.emrexample.mobile.util

import android.view.Window
import android.view.WindowManager

fun makewindowfullScreen(window: Window) {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}