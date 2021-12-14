package com.danjinae.view.join

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityWebViewAuthBinding
import com.danjinae.network.JsHandler
import com.danjinae.view.LoginActivity


class WebViewAuthActivity : AppCompatActivity() {
    val TAG: String = "인증"
    var imp: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebViewAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var btnConfirm = binding.auth
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

        if(imp?.startsWith("imp") == true)
        {
            btnConfirm.isEnabled = true
        }

        btnConfirm.setOnClickListener {
            val intent = Intent(this, AptAuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                Log.d(TAG,"onPageStarted : ${url}\n")
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                Log.d(TAG,"onPageFinished : ${url}\n")
                if(url?.startsWith("file:///android_asset/auth.html?imp") == true){
                    var impId = url?.subSequence(40,56)
                    if (impId != null) {
                        if(impId.startsWith("imp")){
                            Log.d("imp_id",impId.toString())
                            LoginActivity.prefs.setString("impId",impId.toString())
                            btnConfirm.isEnabled = true
                        }
                    }
                }
                super.onPageFinished(view, url)
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                Log.d(TAG,"onLoadResource : ${url}\n")
                super.onLoadResource(view, url)
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                Log.d(TAG,"onReceivedError : ${error?.description.toString()}\n")
                super.onReceivedError(view, request, error)
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                Log.d(TAG,"shouldInterceptRequest : ${request?.url.toString()}\n")
                return super.shouldInterceptRequest(view, request)
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.d(TAG,"shouldOverrideUrlLoading : ${request?.url.toString()}\n")
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

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