package com.example.pelmorexassessment.repo

import com.example.pelmorexassessment.base.Result
import com.example.pelmorexassessment.response.WeatherResponse

interface WeatherRepo {
    suspend fun getWeatherC(cityCode: String): Result<WeatherResponse>

    suspend fun getWeatherF(cityCode: String): Result<WeatherResponse>
}