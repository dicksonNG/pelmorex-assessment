package com.example.pelmorexassessment.repo

import com.example.pelmorexassessment.base.enqueue
import com.example.pelmorexassessment.service.WeatherService
import okhttp3.ResponseBody
import javax.inject.Inject
import com.example.pelmorexassessment.base.Result
import com.example.pelmorexassessment.response.WeatherResponse

class WeatherRepoImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepo {
    override suspend fun getWeatherC(cityCode: String): Result<WeatherResponse> {
        return weatherService.getWeatherC(cityCode).enqueue()
    }

    override suspend fun getWeatherF(cityCode: String): Result<WeatherResponse> {
        return weatherService.getWeatherF(cityCode).enqueue()
    }
}