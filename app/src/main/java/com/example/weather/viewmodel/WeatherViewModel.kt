package com.example.weather.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Weather
import com.example.weather.model.WeatherApi
import kotlinx.coroutines.launch

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val weather: Weather) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}

class WeatherViewModel : ViewModel() {
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init {
        getWeather(latitude = 60.1699, longitude = 24.9384)
    }

    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            weatherUiState = WeatherUiState.Loading

            try {
                val api = WeatherApi.getInstance()
                val result = api.getWeather(latitude = latitude, longitude = longitude)

                weatherUiState = WeatherUiState.Success(result)

            } catch (e: Exception) {
                Log.e("VIEWMODEL", "API Error: ${e.message.toString()}")
                weatherUiState = WeatherUiState.Error("Failed to load weather data.")
            }
        }
    }
}