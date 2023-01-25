package com.mr.touristguide.news.data.remote

import com.mr.touristguide.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(request)
    }
}