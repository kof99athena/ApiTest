package com.athena.apitest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//코틀린 어노테이션 프로세서를 이용해서

data class ApiInfo(val result : RESULT)

data class RESULT(
    @SerializedName("boxOfficeResult") @Expose val result: String,
    @SerializedName("dailyBoxOfficeList") @Expose val detail : List<DETAIL>
)

data class DETAIL( //MOVIEINFO
    @SerializedName("movieNm") @Expose var movieNm : String
)