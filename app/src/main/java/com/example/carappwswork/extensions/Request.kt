package com.example.carappwswork

import android.util.Log
import okhttp3.Request
import okhttp3.RequestBody
import okio.Buffer

fun RequestBody.getJsonString(): String = try {
    val buffer = Buffer()
    writeTo(buffer)
    buffer.readUtf8()
} catch (e: Exception) {
    Log.e("Request", "getJsonString: Can't get Json String from Response Body", e)
    "Can't get Json String from Response Body"
}

fun Request.getHeaders() = if (headers().size() == 0) "Have no headers" else headers().toString()