package com.example.pelmorexassessment.ui.weather

data class WeatherDisplayModel(
    val cityName:String,
    val updateTime:String,
    val condition:String,
    val temperature:String,
    val feelLike:String,
    val temperatureUnit: String,
    val icon:String
)
