package com.hangrycoder.githubrequests

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    val BASE_URL = "https://api.github.com/"

    fun getClient(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(
                //Might need to delete this
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        /*.add(
                            PolymorphicJsonAdapterFactory.of(
                                NetworkResponse::class.java,
                                "response"
                            ).withSubtype(NetworkResponse.Success::class.java, "success")
                                .withSubtype(
                                    NetworkResponse.ServerError::class.java,
                                    "server_error"
                                )
                                .withSubtype(
                                    NetworkResponse.NetworkError::class.java,
                                    "network_error"
                                )
                                .withSubtype(
                                    NetworkResponse.UnknownError::class.java,
                                    "unknown_error"
                                )
                        )*/
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(client)
            .build()
    }
}