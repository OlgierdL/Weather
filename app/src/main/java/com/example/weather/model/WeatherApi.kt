package com.example.weather.model
import com.example.weather.model.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.open-meteo.com/v1/"

interface WeatherApi {

    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String = "temperature_2m"
    ): Weather

    companion object {
        private var weatherService: WeatherApi? = null

        fun getInstance(): WeatherApi {
            if (weatherService == null) {
                weatherService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(WeatherApi::class.java)
            }
            return weatherService!!
        }
    }
}