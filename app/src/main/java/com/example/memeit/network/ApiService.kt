package com.example.memeit.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.imgflip.com/"

// Moshi Object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// build a retrofit object
// 1. with converter factory
// 2. with base url
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// how it talks to web server
interface ApiService {
    @GET("get_memes")
    suspend fun getMemes(): ResponseWrapper
}

// expose the service to the rest of the app using object declaration.
// public singleton object
object Api {
    //You make this lazy initialization, to make sure it is initialized at its first usage
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}