package com.giosoft.savemywallet.base

import android.os.Bundle
import android.view.WindowManager

abstract class FullScreenBaseActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }//onCreate()
}