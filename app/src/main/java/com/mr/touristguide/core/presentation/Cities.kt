package com.mr.touristguide.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.core.model.City
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.mr.touristguide.weather.presentation.WeatherCard
import com.mr.touristguide.weather.presentation.WeatherForecast
import com.mr.touristguide.weather.presentation.WeatherState
import com.mr.touristguide.weather.presentation.WeatherViewModel
import com.mr.touristguide.weather.presentation.colors.DarkBlue
import com.mr.touristguide.weather.presentation.colors.DeepBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityList(cities: List<City>, modifier: Modifier = Modifier, onItemClick: (City) -> Unit, onFloatingButtonClick: () -> Unit) {
    Scaffold(modifier = modifier,
    floatingActionButton = {
        FloatingActionButton(onClick = { onFloatingButtonClick()},
        shape= CircleShape) {


        }
    }) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding(), bottom=it.calculateBottomPadding())) {
            items(items = cities) { item ->
                Surface(
                    modifier = Modifier.padding(all = 4.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onItemClick(item) }) {
                        Text(text = item.name, style = TextStyle(fontSize = 16.sp))
                    }
                }
            }
        }
    }
    
}

@Composable
fun CityDetails(city: City, modifier: Modifier = Modifier, openWeather: (id: Int) -> Unit) {
//    var index = 1;
//    if(id != null)
//    {
//        index = id
//    }
//    val city = cityDao.getById(index)
    val scrollState = rememberScrollState()
    val nameStyle = TextStyle(fontSize=40.sp, fontStyle = FontStyle.Italic)
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = city.name, style=nameStyle)
        }
        Divider(color = Color.Black, thickness = 1.dp)
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        Row(modifier= Modifier.fillMaxWidth()) {
            Text(text=city.mainDescription, fontSize=16.sp)
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Button(onClick = { openWeather(city.id)}) {

            }
        }

    }



}

@Composable
fun CityWeather(city: City, weatherState: WeatherState) {
//    var index = 1;
//    if(id != null)
//    {
//        index = id
//    }
//    val city = cityDao.getById(index)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            WeatherCard(
                state = weatherState,
                backgroundColor = DeepBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(state = weatherState)
        }
//        if(weatherState.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
        weatherState.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}