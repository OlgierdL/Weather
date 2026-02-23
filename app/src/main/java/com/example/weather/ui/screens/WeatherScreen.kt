package com.example.weather.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import com.example.weather.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weather.ui.components.CitiesList
import com.example.weather.ui.components.Heading
import com.example.weather.ui.components.MainTopAppBar
import com.example.weather.viewmodel.WeatherUiState
import com.example.weather.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen( navController: NavController, modifier: Modifier = Modifier, viewModel: WeatherViewModel = viewModel()) {
    var city by remember { mutableStateOf("Helsinki") }
    Scaffold(
        topBar = { MainTopAppBar(stringResource(R.string.weather), navController) },
    ) { innerPadding ->
        Column (
            modifier = modifier
                .padding(innerPadding)
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Heading(stringResource(R.string.title))
            CitiesList(onClick={ city = it })
            Button(
                onClick = {
                    val coordinates = when (city) {
                        "Helsinki" -> Pair(60.1699, 24.9384)
                        "Oulu" -> Pair(65.0121, 25.4651)
                        "Utsjoki" -> Pair(69.9086, 27.0284)
                        else -> null
                    }

                    if (coordinates != null) {
                        viewModel.getWeather(coordinates.first, coordinates.second)
                    }
                },
                enabled = city.isNotEmpty()
            ) {
                Text(stringResource(R.string.get_weather))
            }
            when (val state = viewModel.weatherUiState) {
                is WeatherUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is WeatherUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                is WeatherUiState.Success -> {
                    val currentTemp = state.weather.hourly.temperature2m.firstOrNull() ?: stringResource(
                        R.string.n_a
                    )

                    Text(
                        text = stringResource(R.string.current_temperature_in_c, city, currentTemp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

}