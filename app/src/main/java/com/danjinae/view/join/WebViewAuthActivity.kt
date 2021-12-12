package com.danjinae.view.join

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityWebViewAuthBinding
import com.danjinae.network.JsHandler


class WebViewAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebViewAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mContainer = binding.wViewFrame
        var mWebView = binding.wView
        var mWebViewPop = binding.wView
        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
        }

        var cookieManager: CookieManager = CookieManager.getInstance()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.builtInZoomControls = true
        mWebView.settings.domStorageEnabled = true
        mWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        mWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebView.settings.loadsImagesAutomatically = true
        mWebView.settings.builtInZoomControls = true
        mWebView.settings.setSupportZoom(true)
        mWebView.settings.setSupportMultipleWindows(false)
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.useWideViewPort = true
        WebView.setWebContentsDebuggingEnabled(true)

        mWebView.webChromeClient = WebChromeClient()
        mWebView.webViewClient = WebViewClient()
        mWebView.addJavascriptInterface(JsHandler(), "AndroidBridge")
        mWebView.loadUrl("file:///android_asset/auth.html")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}