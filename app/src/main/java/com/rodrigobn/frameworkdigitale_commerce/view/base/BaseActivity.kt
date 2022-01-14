package com.rodrigobn.frameworkdigitale_commerce.view.base

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.rodrigobn.frameworkdigitale_commerce.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()
    }

    /**
     * Init configuration activity
     */
    abstract fun init()

    /**
     * Get layout activity
     */
    abstract fun getLayout() : Int

    /**
     * Set color status bar
     */
    fun setupStatusBar() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.colorPrimary)
    }
}