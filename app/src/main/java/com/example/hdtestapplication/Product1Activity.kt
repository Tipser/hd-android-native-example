package com.example.hdtestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class Product1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product1)


        val webView = findViewById<WebView>(R.id.product1webView);
        webView.webViewClient = WebViewClient()
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(" http://hdpasset.hd.se/Tipser/5ea81a8c789dea0001a8e344")
    }
}