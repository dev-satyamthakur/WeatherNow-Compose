package com.satyamthakur.learning.weathernow.di

import com.satyamthakur.learning.weathernow.location.DefaultLocationTracker
import com.satyamthakur.learning.weathernow.location.LocationTracker
import com.satyamthakur.learning.weathernow.repository.WeatherRepository
import com.satyamthakur.learning.weathernow.repository.WeatherRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepositoryImplementation
    ): WeatherRepository
}