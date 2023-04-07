@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.touristguide

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.ExperimentalPagingApi
import com.mr.touristguide.core.presentation.NavigationDrawerScreen
import com.mr.touristguide.core.presentation.data.GuideViewModel
import com.mr.touristguide.core.presentation.data.SettingsViewModel
import com.mr.touristguide.news.presentation.NewsViewModel
import com.mr.touristguide.ui.theme.TouristGuideTheme
import com.mr.touristguide.weather.presentation.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: GuideViewModel by viewModels()
//    private val weatherViewModel: WeatherViewModel by viewModels()
    private val newsViewModel: NewsViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val locale = stringResource(id = R.string.locale)
//            viewModel.loadEverything(locale)
            TouristGuideTheme {
                NavigationDrawerScreen(
                    cities = viewModel.citiesState.cities,
                    landmarks = viewModel.landmarksState.landmarks,
                    country = viewModel.country.value,
//                    weatherState = weatherViewModel.state,
                    newsState = newsViewModel.state,
//                    loadWeather = { city -> weatherViewModel.loadWeatherInfo(city) },
                    settingsViewModel = settingsViewModel,
                    guideViewModel = viewModel
                )

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