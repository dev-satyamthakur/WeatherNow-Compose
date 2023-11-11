package com.satyamthakur.learning.weathernow.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.satyamthakur.learning.weathernow.domain.weather.WeatherData
import com.satyamthakur.learning.weathernow.domain.weather.WeatherInfo
import com.satyamthakur.learning.weathernow.domain.weather.WeatherType
import com.satyamthakur.learning.weathernow.remote.WeatherDTO
import com.satyamthakur.learning.weathernow.remote.WeatherDataDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDTO.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperature[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(index, WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            windSpeed = windSpeed,
            pressure = pressure,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        ))
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDTO.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find{
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )

}