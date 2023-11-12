package com.satyamthakur.learning.weathernow.repository

import com.satyamthakur.learning.weathernow.domain.util.Resource
import com.satyamthakur.learning.weathernow.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}