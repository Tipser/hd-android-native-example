package com.example.hdtestapplication

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class Product1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product1)

        val webView = findViewById<WebView>(R.id.product1webView);
        webView.settings.javaScriptEnabled = true

        val cookieManager: CookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        webView.loadUrl("http://hdpasset.hd.se/Tipser/5ea81a8c789dea0001a8e344")
    }
}