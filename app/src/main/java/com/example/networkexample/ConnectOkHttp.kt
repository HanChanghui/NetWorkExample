package com.example.networkexample

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import okio.use

class ConnectOkHttp {
    val client = OkHttpClient()
    val url = "https://www.naver.com"
    val request = Request.Builder().url(url).build()

    // 동기식
    fun SyncConnectHttp() {
        client.newCall(request).execute().use { response -> {
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            Log.d("body", "body : ${response.body.string()}")
        } }
    }

    // 비동기식
    fun AsyncConnectHttp() {
        client.newCall(request).equals(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("", "onFailure : error ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                Log.d("body", "body : ${response.body.string()}")
            }
        })
    }
}