package com.alif.newsapplication.model.dataSource.network

import com.alif.newsapplication.model.dataSource.network.extentions.NotFoundException
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

object NetworkClient {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addNetworkInterceptor(NetworkInterceptor())
        .authenticator(Autentificator())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "48faf68bd8d243ee964a0421cc0caad5")
                .build()
            return chain.proceed(request)
        }
    }

    private class ErrorInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())

            if (response.code == HttpURLConnection.HTTP_NOT_FOUND) {
                throw NotFoundException()
            }

            return response
        }

    }

    private class NetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())

            if (response.code == 205) {
                return response.newBuilder()
                    .code(401)
                    .build()
            }

            return response
        }

    }

    private class Autentificator : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            return response.request.newBuilder().build()
        }

    }
}