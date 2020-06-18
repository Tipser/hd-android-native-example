package com.example.hdtestapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

object TokenManager {

    fun initializeToken(context: AppCompatActivity) {
        val tokenFromPrefs = loadTokenFromPrefs(context)

        if (tokenFromPrefs != null) {
            installTokenInWebViews(tokenFromPrefs)
        } else {
            generateTokenFromApi {
                installTokenInWebViews(it)
                persistTokenInPrefs(context, it)
            }
        }
    }

    private fun generateTokenFromApi(onSuccess: (token: String) -> Unit) {
        ApiClient.createAnonymousToken {
            onSuccess(it)
        }
    }

    private fun installTokenInWebViews(token: String) {
        Cookie.setValue("tipserToken", token)
    }

    private fun loadTokenFromPrefs(context: AppCompatActivity): String? {
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return null
        return sharedPref.getString("tipserToken", null)
    }

    private fun persistTokenInPrefs(context: AppCompatActivity, token: String) {
        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("tipserToken", token)
            commit()
        }
    }

}