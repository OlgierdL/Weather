package com.example.weather.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "weather"
    ) {
        composable(route = "weather") { WeatherScreen(navController) }
        composable(route = "info") { InfoScreen(navController) }
    }
}