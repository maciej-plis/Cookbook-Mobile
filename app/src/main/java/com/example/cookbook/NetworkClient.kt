package com.example.cookbook

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

object NetworkClient {

    private const val BASE_URL: String = "http://192.168.1.8:8082"
    private const val TIMEOUT: Long = 10

    private var retrofit: Retrofit? = null
    val retrofitClient: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .connectTimeout(TIMEOUT, SECONDS)
                            .addInterceptor(HttpLoggingInterceptor().apply { level = BASIC })
                            .build()
                    )
                    .build()
            }
            return retrofit!!
        }
}