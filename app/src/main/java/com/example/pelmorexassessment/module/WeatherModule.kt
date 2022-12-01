package com.example.pelmorexassessment.module

import com.example.pelmorexassessment.repo.WeatherRepo
import com.example.pelmorexassessment.repo.WeatherRepoImpl
import com.example.pelmorexassessment.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherService(
        retrofit: Retrofit
    ): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherService: WeatherService): WeatherRepo {
        return WeatherRepoImpl(
            weatherService)
    }
}