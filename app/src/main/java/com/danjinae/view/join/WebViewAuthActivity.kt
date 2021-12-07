package com.danjinae.view.join

import android.net.http.SslError
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.danjinae.databinding.ActivityWebViewAuthBinding
import com.danjinae.network.JsHandler


class WebViewAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebViewAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var mWebView = binding.wView

        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.builtInZoomControls = true
        mWebView.settings.domStorageEnabled = true
//        mWebView.settings.javaScriptCanOpenWindowsAutomatically = true
//        mWebView.settings.setSupportMultipleWindows(true)

        mWebView.webChromeClient = WebChromeClient()
        mWebView.webViewClient = WebViewClient()
        mWebView.addJavascriptInterface(JsHandler(),"AndroidBridge")
        mWebView.loadUrl("file:///android_asset/auth.html")
    }
}

class NewWebViewClient: WebViewClient() { override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse? ) { super.onReceivedHttpError(view, request, errorResponse) } override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError? ) { super.onReceivedError(view, request, error) } override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError? ) { } }