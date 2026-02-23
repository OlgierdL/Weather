package com.example.weather.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("generationtime_ms") val generationtimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerializedName("hourly_units") val hourlyUnits: HourlyUnits,
    val hourly: Hourly
)

data class HourlyUnits(
    val time: String,
    @SerializedName("temperature_2m") val temperature2m: String
)

data class Hourly(
    val time: List<String>,
    @SerializedName("temperature_2m") val temperature2m: List<Double>
)