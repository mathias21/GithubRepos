package com.batcuevasoft.githubrepo.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class CommonInterceptor @Inject constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            addHeader(ACCEPT_HEADER, ACCEPT)
            addHeader(API_VERSION_HEADER, API_VERSION)
        }.build()

        return chain.proceed(request)
    }

    companion object {
        const val ACCEPT_HEADER = "Accept"
        const val ACCEPT = "application/vnd.github+json"
        const val API_VERSION_HEADER = "X-GitHub-Api-Version"
        const val API_VERSION = "2022-11-28"
    }
}