package com.mr.touristguide.core.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.mr.touristguide.R
import com.mr.touristguide.core.data.mappers.sectionsToAnnotatedString
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Section
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.mr.touristguide.ui.theme.*
import com.mr.touristguide.weather.presentation.WeatherCard
import com.mr.touristguide.weather.presentation.WeatherForecast
import com.mr.touristguide.weather.presentation.WeatherViewModel
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
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = stringResource(id = R.string.map_of_cities_cd))
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
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text(
                            text = item.name,
                            style = textNormal,
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
    var expanded by rememberSaveable { mutableStateOf(false)}
//    val nameStyle = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Italic)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = city.name, style = titleLargeItalic)
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
                        style = textLargeItalic,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                                style = textSmall
                            )
                            Text(
                                text = property.value,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                style = textSmall
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(all = 4.dp)
                ) {
                    Text(
                        modifier = Modifier.animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                            .clickable { expanded = !expanded },
                        text = sectionsToAnnotatedString(city.sections),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = if(expanded) Int.MAX_VALUE else 10,
                        overflow = TextOverflow.Ellipsis,
//                        onTextLayout = {
//                            if(it.hasVisualOverflow){
//                                expanded = true;
//                            }
//                        }
                    )
                    if(!expanded) {
                        Button(onClick = { expanded = !expanded }) {
                            Text(text = stringResource(id = R.string.show_more))

                        }
                    }
                }
            }
        }
        items(items = images, key = { unsplashImage -> unsplashImage.id }) { unsplashImage ->
            unsplashImage?.let {
                UnsplashItem(unsplashImage = it)
            }
        }
        item{
            Spacer(modifier = Modifier.height(12.dp))
        }

    }


}

@Composable
fun CityWeather(city: City, weatherViewModel: WeatherViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            WeatherCard(
                state = weatherViewModel.state,
                backgroundColor = DeepBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(state = weatherViewModel.state)
        }
//        if(weatherState.isLoading) {
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
        weatherViewModel.state.error?.let { error ->
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
