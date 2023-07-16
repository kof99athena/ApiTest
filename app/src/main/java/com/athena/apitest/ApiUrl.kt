package com.athena.apitest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


class ApiUrl {
    //companion object를 static으로 이해하면 안된다.
    //kotlin에서는 static 키워드 쓰지 않는다. 대체 키워드 : companion object
    //별도의 객체생성없이 클래스명으로만으로도 접근 가능하다
    companion object{
        const val EndPoint ="searchDailyBoxOfficeList.json?"
        const val API_KEY = "f5eef3421c602c6cb7ea224104795888"
    }

    interface ApiUrlInterface{
        @GET(ApiUrl.EndPoint)
        suspend fun getInfoData(//getDailyBoxOffice
            @Query("targetDt")
            targetdate : String,
            @Query("key")
            key:String = ApiUrl.API_KEY
        ): Response<ApiInfo>
    }//ApiUrlInterface
}