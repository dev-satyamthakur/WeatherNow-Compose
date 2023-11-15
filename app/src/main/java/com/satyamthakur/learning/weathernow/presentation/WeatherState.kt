package com.satyamthakur.learning.weathernow.presentation

import com.satyamthakur.learning.weathernow.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
