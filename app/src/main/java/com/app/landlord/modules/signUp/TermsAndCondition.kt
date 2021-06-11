package com.app.landlord.modules.signUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.landlord.R
import com.app.landlord.databinding.DialogTermsAndConditionBinding
import com.app.landlord.utils.SignUpConst

class TermsAndCondition : AppCompatActivity(), View.OnClickListener {
    var termsAndConditionBinding: DialogTermsAndConditionBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        termsAndConditionBinding = DialogTermsAndConditionBinding.inflate(layoutInflater)
        setContentView(termsAndConditionBinding!!.root)
        if (intent != null) {
            if (intent.getBooleanExtra(SignUpConst.RRIVACY_POLICY, false)) {
                termsAndConditionBinding!!.heading.setText("Privacy Policy")
                termsAndConditionBinding!!.bottomBtns.visibility = View.GONE

            }
        }
//        loadWebView()
        performOnClick()

    }

    private fun performOnClick() {
        termsAndConditionBinding!!.close.setOnClickListener(this)
        termsAndConditionBinding!!.decline.setOnClickListener(this)
        termsAndConditionBinding!!.accept.setOnClickListener(this)
    }


    private fun loadWebView() {
        termsAndConditionBinding!!.webview.settings.setJavaScriptEnabled(true)
        termsAndConditionBinding!!.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        termsAndConditionBinding!!.webview.loadUrl("http://api.tapdatingapp.com/terms_of_service")
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.close -> {
                finish()
            }
            R.id.decline -> {
                finish()
            }
            R.id.accept -> {
                finish()
            }
        }
    }

}