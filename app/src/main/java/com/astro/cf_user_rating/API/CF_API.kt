package com.astro.cf_user_rating.API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CF_API {

    @GET("user.rating")
    fun  getUserRating(@Query("handle")userName:String
    ): Call<CF_data>

    companion object{
        operator fun invoke() : CF_API{
            return Retrofit.Builder()
//                .client(okhttp3.OkHttpClient())
                .baseUrl("https://codeforces.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CF_API::class.java)
        }
    }
}