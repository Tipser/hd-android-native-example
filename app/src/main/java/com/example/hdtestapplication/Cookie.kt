package com.example.hdtestapplication

import android.webkit.CookieManager

object Cookie {
    private const val COOKIE_DOMAIN = "https://www.tipser.com"
    private val cookieManager = CookieManager.getInstance()

    init {
        cookieManager.setAcceptCookie(true)
    }

    private fun getListOfCookieValues(): List<String>? {
        val cookie = cookieManager.getCookie(COOKIE_DOMAIN) ?: return null

        return cookie
            .split(";")
            .map { v -> v.trim() }
    }

    fun setValue(key: String, value: String) {
        cookieManager.setCookie(COOKIE_DOMAIN, "$key=$value;")
    }

    fun getValue(key: String): String? {
        val cookieValues = this.getListOfCookieValues() ?: return null

        val cookieValue = cookieValues
            .find { v ->
                v.startsWith("$key=")
            }
        return if (cookieValue != null) {
            cookieValue.split("=")[1]
        } else {
            null
        }
    }
}