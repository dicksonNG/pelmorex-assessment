package com.example.pelmorexassessment.service

import com.example.pelmorexassessment.response.WeatherResponse
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("{cityCode}/c")
    fun getWeatherC(
        @Path("cityCode") cityCode: String,
    ): Call<WeatherResponse>

    @GET("{cityCode}/f")
    fun getWeatherF(
        @Path("cityCode") cityCode: String,
    ): Call<WeatherResponse>
}