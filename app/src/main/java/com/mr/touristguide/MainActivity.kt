@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.touristguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mr.touristguide.core.presentation.NavigationDrawerScreen
import com.mr.touristguide.core.presentation.data.GuideViewModel
import com.mr.touristguide.news.presentation.NewsViewModel
import com.mr.touristguide.ui.theme.TouristGuideTheme
import com.mr.touristguide.weather.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GuideViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val newsViewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristGuideTheme {
                NavigationDrawerScreen(cities = viewModel.citiesState.cities, landmarks = viewModel.landmarksState.landmarks, weatherState = weatherViewModel.state, newsState = newsViewModel.state , loadWeather = { city -> weatherViewModel.loadWeatherInfo(city) })

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TouristGuideTheme {
        Greeting("Android")
    }
}