package com.athena.apitest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val baseUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/"

    fun getRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRetrofitService(): ApiUrl.ApiUrlInterface = getRetrofit().create(ApiUrl.ApiUrlInterface::class.java)

}