package com.mr.touristguide.core.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.core.model.City
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.mr.touristguide.weather.presentation.WeatherCard
import com.mr.touristguide.weather.presentation.WeatherForecast
import com.mr.touristguide.weather.presentation.WeatherState
import com.mr.touristguide.weather.presentation.WeatherViewModel
import com.mr.touristguide.weather.presentation.colors.DarkBlue
import com.mr.touristguide.weather.presentation.colors.DeepBlue
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.mr.touristguide.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityList(cities: List<City>, modifier: Modifier = Modifier, onItemClick: (City) -> Unit, onFloatingButtonClick: () -> Unit) {
    Scaffold(modifier = modifier,
    floatingActionButton = {
        FloatingActionButton(onClick = { onFloatingButtonClick()},
        shape= CircleShape, containerColor = MaterialTheme.colorScheme.secondary, contentColor = MaterialTheme.colorScheme.onSecondary) {


        }
    }) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())) {
            items(items = cities) { item ->
                Surface(
                    modifier = Modifier.padding(all = 4.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { onItemClick(item) }
                    ) {
                        Text(text = item.name, style = TextStyle(fontSize = 16.sp))
//                        Button(onClick = { /*TODO*/ }) {
//                            Text(text = "Weather")
//                        }
                    }
                }
            }
        }
    }
    
}

@OptIn(ExperimentalPagingApi::class)
@Composable
fun CityDetails(city: City, modifier: Modifier = Modifier, openWeather: (id: Int) -> Unit, showOnMap: () -> Unit, searchViewModel: SearchViewModel) {
    val images = searchViewModel.searchedImages.collectAsLazyPagingItems()
    val nameStyle = TextStyle(fontSize=40.sp, fontStyle = FontStyle.Italic)
    val context = LocalContext.current
//    val youtubePlayerView = YouTubePlayerView(context)
//    LocalLifecycleOwner.current.lifecycle.addObserver(youtubePlayerView)
//    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//        override fun onReady(youTubePlayer: YouTubePlayer) {
//            super.onReady(youTubePlayer)
////            youTubePlayer.loadVideo("5g4fhqCSdLQ", 0f)
//            youTubePlayer.cueVideo("5g4fhqCSdLQ", 0f)
//        }
//    })
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = city.name, style = nameStyle)
            }
        }
//        item {
//            Divider(color = Color.Black, thickness = 1.dp)
//        }
//        item{
//            Spacer(modifier = Modifier.height(5.dp))
//        }
        item{
            Surface(modifier = Modifier
                .fillMaxWidth(), shape= RoundedCornerShape(8.dp), shadowElevation = 5.dp) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(all = 4.dp)){
                    Text(
                        text = city.shortDescription,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 18.sp
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { openWeather(city.id) }) {
                    Text(text = stringResource(id = R.string.view_weather_forecast))

                }
                Button(onClick = { showOnMap() }) {
                    Text(text = stringResource(id = R.string.show_on_map))
                }
            }
        }
//        item{
//            Spacer(modifier = Modifier
//                .height(10.dp)
//                .background(Color.Transparent))
//        }
//        item{
//            Surface(shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp) {
//                AndroidView(factory = {
////                var view = YouTubePlayerView(it)
////                LocalLifecycleOwner.current.lifecycle.addObserver(view)
////                val fragment = view.addYouTubePlayerListener(
////                    object : AbstractYouTubePlayerListener() {
////                        override fun onReady(youTubePlayer: YouTubePlayer) {
////                            super.onReady(youTubePlayer)
////                            youTubePlayer.loadVideo("5g4fhqCSdLQ", 0f)
////                        }
////                    }
////                )
////                view
//                    youtubePlayerView
//                })
//            }
//        }
//        item{
//            Spacer(modifier= Modifier.height(5.dp))
//        }
        item{
            Surface(modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(all = 4.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "property1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Text(text = "value1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "property1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Text(text = "value1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "property1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Text(text = "value1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "property1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Text(text = "value1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "property1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                        Text(text = "value1", color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                }
            }
        }
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        item {
            Surface(modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(all = 4.dp)) {
                    Text(text = city.mainDescription, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
//        item{
//            Spacer(modifier = Modifier.height(5.dp))
//        }
//        items(items = images, key = {unsplashImage -> unsplashImage.id}){
//                unsplashImage -> unsplashImage?.let{ UnsplashItem(unsplashImage = it)}
//        }
//        item{
//            LazyRow(
//                modifier = Modifier.fillMaxWidth().height(500.dp).background(Color.Black),
//                contentPadding = PaddingValues(all = 12.dp),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)){
//                items(items = images, key = {unsplashImage -> unsplashImage.id}){
//                        unsplashImage -> unsplashImage?.let{ UnsplashItem(unsplashImage = it)}
//                }
//            }
//        }
        items(items = images, key = {unsplashImage -> unsplashImage.id}){
                unsplashImage -> unsplashImage?.let{
//            Spacer(modifier = Modifier.height(12.dp))
            UnsplashItem(unsplashImage = it)}
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

@Preview
@Composable
fun CityDetailsPreview() {

}