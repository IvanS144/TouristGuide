package com.mr.touristguide.core.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.mr.touristguide.R
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.mr.touristguide.weather.presentation.WeatherCard
import com.mr.touristguide.weather.presentation.WeatherForecast
import com.mr.touristguide.weather.presentation.WeatherState
import com.mr.touristguide.weather.presentation.colors.DarkBlue
import com.mr.touristguide.weather.presentation.colors.DeepBlue
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityList(
    cities: List<City>,
    modifier: Modifier = Modifier,
    onItemClick: (City) -> Unit,
    onFloatingButtonClick: () -> Unit
) {
    Scaffold(modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFloatingButtonClick() },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            items(items = cities) { item ->
                Surface(
                    modifier = Modifier.padding(all = 4.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { onItemClick(item) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val model = if (item.flagUrl.endsWith("svg")) {
                            ImageRequest.Builder(LocalContext.current)
                                .data(item.flagUrl)
                                .decoderFactory(SvgDecoder.Factory())
                                .build()
                        } else {
                            ImageRequest.Builder(LocalContext.current)
                                .data(item.flagUrl)
                                .build()

                        }
                        AsyncImage(
                            model = model,
                            contentDescription = "Flag of" + item.name,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text(
                            text = item.name,
                            style = TextStyle(fontSize = 16.sp),
                            modifier = Modifier.weight(1f)
                        )
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
fun CityDetails(
    city: City,
    modifier: Modifier = Modifier,
    openWeather: (id: Int) -> Unit,
    showOnMap: () -> Unit,
    searchViewModel: SearchViewModel
) {
    val images = searchViewModel.searchedImages.collectAsLazyPagingItems()
    val nameStyle = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Italic)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = city.name, style = nameStyle)
            }
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(all = 4.dp)
                ) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { openWeather(city.id) }) {
                    Text(text = stringResource(id = R.string.view_weather_forecast))

                }
                Button(onClick = { showOnMap() }) {
                    Text(text = stringResource(id = R.string.show_on_map))
                }
            }
        }
        item {
            Surface(shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp) {
                AndroidView(factory = {
                    var view = YouTubePlayerView(it)
//                LocalLifecycleOwner.current.lifecycle.addObserver(view)
                    val fragment = view.addYouTubePlayerListener(
                        object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.cueVideo(city.videoUrl, 0f)
                            }
                        }
                    )
                    view
                })
            }
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(all = 4.dp)
                ) {
                    for (property in city.properties) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = property.key,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                fontSize = 14.sp
                            )
                            Text(
                                text = property.value,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(all = 4.dp)
                ) {
                    Text(
                        text = city.getDescription(),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        items(items = images, key = { unsplashImage -> unsplashImage.id }) { unsplashImage ->
            unsplashImage?.let {
                UnsplashItem(unsplashImage = it)
            }
        }

    }


}

@Composable
fun CityWeather(city: City, weatherState: WeatherState) {
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