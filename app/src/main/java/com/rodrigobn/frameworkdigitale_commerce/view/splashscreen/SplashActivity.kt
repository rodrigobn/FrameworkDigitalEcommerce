package com.rodrigobn.frameworkdigitale_commerce.view.splashscreen

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.rodrigobn.frameworkdigitale_commerce.view.auth.LoginActivity
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        setupStatusBar()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}