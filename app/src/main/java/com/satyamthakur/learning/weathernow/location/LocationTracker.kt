package com.satyamthakur.learning.weathernow.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}