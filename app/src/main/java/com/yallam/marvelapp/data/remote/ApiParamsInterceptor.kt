package com.yallam.marvelapp.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by Yahia Allam on 15/06/2019
 *
 * Interceptor to add (api key, timestamp, hash) query params to all requests
 */
class ApiParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val timestamp = System.currentTimeMillis()
        val url = originalUrl.newBuilder()
            .addQueryParameter(NetworkConstants.AUTH_PARAM_KEY, NetworkConstants.AUTH_PUBLIC_API_KEY)
            .addQueryParameter(NetworkConstants.TIMESTAMP_PARAM_KEY, timestamp.toString())
            .addQueryParameter(
                NetworkConstants.HASH_PARAM_KEY,
                calculateHash(timestamp, NetworkConstants.AUTH_PRIVATE_API_KEY, NetworkConstants.AUTH_PUBLIC_API_KEY)
            ).build()

        val request = original.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    //TODO: extract from here
    private fun calculateHash(timestamp: Long, privateApiKey: String, publicApiKey: String): String {
        val str = timestamp.toString() + privateApiKey + publicApiKey
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(str.toByteArray())).toString(16).padStart(32, '0')
    }
}