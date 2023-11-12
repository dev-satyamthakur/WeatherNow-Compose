package com.satyamthakur.learning.weathernow.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.satyamthakur.learning.weathernow.domain.util.Resource
import com.satyamthakur.learning.weathernow.domain.weather.WeatherInfo
import com.satyamthakur.learning.weathernow.mappers.toWeatherInfo
import com.satyamthakur.learning.weathernow.remote.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latitude = lat, longitude = lat
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occured")
        }
    }
}