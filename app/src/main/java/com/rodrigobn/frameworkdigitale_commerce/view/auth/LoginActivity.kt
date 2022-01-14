package com.rodrigobn.frameworkdigitale_commerce.view.auth

import android.content.Intent
import android.view.View
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Profile
import com.rodrigobn.frameworkdigitale_commerce.prefs
import com.rodrigobn.frameworkdigitale_commerce.view.main.MainActivity
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun init() {
        setupStatusBar()
        showError(false, "")

        login.setOnClickListener {
            showError(false, "")
            if (isNameValid(edt_name.text.toString())) {
                if (isPassword(edt_password.text.toString())) {
                    val profile = Profile(edt_name.text.toString(), edt_password.text.toString())
                    prefs.nameProfile = profile.name
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    showError(true, getString(R.string.invalid_password))
                }
            } else {
                showError(true, getString(R.string.invalid_name))
            }
        }
    }

    /**
     * Validate password
     */
    private fun isPassword(password: String) = password.isNotEmpty()

    /**
     * Validate name
     */
    private fun isNameValid(name: String) = name.length > 3

    /**
     * Show error message
     */
    private fun showError(enabled: Boolean, message: String) {
        if (enabled) {
            error_message.visibility = View.VISIBLE
            error_text.text = message
        } else {
            error_message.visibility = View.INVISIBLE
        }
    }
}