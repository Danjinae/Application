package com.danjinae.network

import android.util.Log
import android.webkit.JavascriptInterface

class JsHandler {

    @JavascriptInterface
    fun resultAuth(message: String) {
        Log.d("성공",message)
    }
}