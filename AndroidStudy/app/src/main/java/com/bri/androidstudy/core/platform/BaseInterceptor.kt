package com.bri.androidstudy.core.platform

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val orgRequest = chain.request()
        val request = orgRequest.newBuilder().apply {
            addHeader("Accept", "application/vnd.github.v3+json")
        }.build()

        return chain.proceed(request)
    }
}