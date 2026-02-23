package com.example.weather.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.weather.R
import com.example.weather.ui.components.ScreenTopBar

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
            topBar = { ScreenTopBar(stringResource(R.string.info),navController) },
    ) { innerPadding ->
        Text(text="This small app has been developed as an assignment for the ID00CS48-3005 course. It uses the Open-Meteo API and was written by Olgierd Ludwiczak.",modifier = Modifier.padding(innerPadding))
    }
}