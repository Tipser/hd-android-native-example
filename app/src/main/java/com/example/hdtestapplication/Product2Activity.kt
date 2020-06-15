package com.example.hdtestapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class Product2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product2)

        val webView = findViewById<WebView>(R.id.product2webView);
        webView.webViewClient = WebViewClient()
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://hdpasset.hd.se/Tipser/5d9efe7f98c9aa000106c76c")
    }
}