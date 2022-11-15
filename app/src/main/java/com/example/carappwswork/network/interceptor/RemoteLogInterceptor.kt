package com.example.carappwswork.network.interceptor

import android.util.Log
import com.example.carappwswork.getHeaders
import com.example.carappwswork.getJsonString
import okhttp3.Interceptor
import okhttp3.Response

class RemoteLogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.i("Request",
            String.format(
                "\nrequest:%s %s" + "\n" + "headers:%s" + "\n" + "body: %s",
                request.url(),
                request.method(),
                request.getHeaders(),
                request.body()?.getJsonString()
            )
        )
        return chain.proceed(request)
    }
}