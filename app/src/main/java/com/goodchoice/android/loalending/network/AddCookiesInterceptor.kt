package com.goodchoice.android.loalending.network

import com.goodchoice.android.loalending.App
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder=chain.request().newBuilder()
        val cookies= App.cookie

        for(cookie in cookies){
            builder.addHeader("Cookie",cookie)
        }
        return chain.proceed(builder.build())
    }
}