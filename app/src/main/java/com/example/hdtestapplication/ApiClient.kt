package com.example.hdtestapplication

import okhttp3.*
import okio.IOException
import org.json.JSONException
import org.json.JSONObject

object ApiClient {
    private val client = OkHttpClient()

    fun getCartSize(token: String, onSuccess: (cartSize: Int) -> Unit) {
        val request: Request = Request.Builder()
            .url("https://t3-prod-api.tipser.com/v3/shoppingcart")
            .addHeader("authorization", "Bearer $token")
            .addHeader("content-type", "application/json")
            .build()

        val call: Call = client.newCall(request)
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string();
                try {
                    val jsonObject = JSONObject(responseData)
                    val numberOfProducts = jsonObject.getInt("numberOfProducts")
                    onSuccess(numberOfProducts)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

    fun createAnonymousToken(onSuccess: (token: String) -> Unit) {
        val request: Request = Request.Builder()
            .url("https://t3-prod-api.tipser.com/v3/auth/anonymousToken")
            .build()

        val call: Call = client.newCall(request)
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val token = response.body!!.string();
                onSuccess(token)
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

}