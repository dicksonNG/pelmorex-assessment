package com.example.pelmorexassessment.ui.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pelmorexassessment.DateUtil
import com.example.pelmorexassessment.base.BaseViewModel
import com.example.pelmorexassessment.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.pelmorexassessment.base.Result

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : BaseViewModel() {

    private val selectedCity = MutableLiveData(City.TORONTO)
    private val selectedScale = MutableLiveData(TemperatureScale.CELCIUS)

    private val _weatherData = MutableLiveData<WeatherDisplayModel>()
    val weatherData: MutableLiveData<WeatherDisplayModel> get() = _weatherData

    fun getWeatherC(cityCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = weatherRepo.getWeatherC(cityCode)) {
                is Result.Success -> {
                    val cityName = mapPaymentType(result.data.placecode).cityName
                    val item = WeatherDisplayModel(
                        cityName = cityName,
                        updateTime = DateUtil.getDateTime(result.data.updatetimeStampGmt)?:result.data.updatetime,
                        condition = result.data.wxcondition,
                        temperature = result.data.temperature,
                        feelLike = result.data.feelsLike,
                        temperatureUnit = result.data.temperatureUnit,
                        icon = result.data.inic
                    )
                    _weatherData.postValue(item)
                }
                is Result.Failure -> {
                }
            }
        }
    }

    fun getWeatherF(cityCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = weatherRepo.getWeatherF(cityCode)) {
                is Result.Success -> {
                    val cityName = mapPaymentType(result.data.placecode).cityName
                    val item = WeatherDisplayModel(
                        cityName = cityName,
                        updateTime = result.data.updatetime,
                        condition = result.data.wxcondition,
                        temperature = result.data.temperature,
                        feelLike = result.data.feelsLike,
                        temperatureUnit = result.data.temperatureUnit,
                        icon = result.data.inic
                    )
                    _weatherData.postValue(item)
                }
                is Result.Failure -> {
                }
            }
        }
    }


    fun getCityList(): List<City> {
        return listOf(
            City.TORONTO,
            City.MONTREAL,
            City.OTTAWA,
            City.VANCOUVER,
            City.CALGARY
        )
    }

    fun getSelectedCity(): City {
        return selectedCity.value!!
    }

    fun setSelectedCity(city: City) {
        callApi(city, getSelectedScale())
        selectedCity.postValue(city)
    }

    fun callApi(city: City, scale: TemperatureScale) {
        when (scale) {
            TemperatureScale.CELCIUS -> {
                getWeatherC(city.cityCode)
            }
            else -> {
                getWeatherF(city.cityCode)
            }
        }
    }


    fun setSelectedScale(scale: TemperatureScale) {
        callApi(getSelectedCity(), scale)
        selectedScale.postValue(scale)
    }

    fun getSelectedScale(): TemperatureScale {
        return selectedScale.value!!
    }

    private fun mapPaymentType(paymentMethod: String): City {
        return when (paymentMethod) {
            City.TORONTO.cityCode -> City.TORONTO
            City.MONTREAL.cityCode -> City.MONTREAL
            City.OTTAWA.cityCode -> City.OTTAWA
            City.VANCOUVER.cityCode -> City.VANCOUVER
            City.CALGARY.cityCode -> City.CALGARY
            else -> City.NOT_FOUND
        }
    }

    enum class TemperatureScale {
        CELCIUS,
        FAHRENHEIT
    }

    enum class City(val cityName: String, val cityCode: String) {
        TORONTO("Toronto", "CAON0696"),
        MONTREAL("Montreal", "CAON0423"),
        OTTAWA("Ottawa", "CAON0512"),
        VANCOUVER("Vancouver", "CABC0308"),
        CALGARY("Calgary", "CAAB0049"),
        NOT_FOUND("", "");

        override fun toString(): String {
            return cityName
        }
    }
}