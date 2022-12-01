package com.example.pelmorexassessment.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("updatetime")
    val updatetime:String,
    @SerializedName("updatetime_stamp_gmt")
    val updatetimeStampGmt:String,
    @SerializedName("wxcondition")
    val wxcondition:String,
    @SerializedName("icon")
    val icon:String,
    @SerializedName("inic")
    val inic:String,
    @SerializedName("temperature")
    val temperature:String,
    @SerializedName("feels_like")
    val feelsLike:String,
    @SerializedName("temperature_unit")
    val temperatureUnit:String,
    @SerializedName("placecode")
    val placecode:String
)
