package com.example.harfinovian.submission1.api

import com.example.harfinovian.submission1.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository {

    companion object {

        val gson = GsonBuilder().create()

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + "api/v1/json/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}