@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.touristguide.core.presentation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.mr.touristguide.R
import com.mr.touristguide.core.model.City
import com.mr.touristguide.core.model.Country
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.core.presentation.data.*
import com.mr.touristguide.news.presentation.Article
import com.mr.touristguide.news.presentation.NewsScreen
import com.mr.touristguide.news.presentation.NewsState
import com.mr.touristguide.news.presentation.NewsViewModel
import com.mr.touristguide.weather.presentation.WeatherState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerScreen(
    cities: List<City>?,
    landmarks: List<Landmark>?,
    country: Country?,
    weatherState: WeatherState,
    newsState: NewsState,
    loadWeather: (City) -> Unit,
    imagesViewModel: ImagesViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel,
    guideViewModel: GuideViewModel
) {
//    val scaffoldState = rememberScaffoldState()
//    var title by remember { mutableStateOf("Home") }
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val route = backStackEntry?.destination?.route ?: "Home"
    val currentScreenTitle: String = if (route == "home") {
        stringResource(id = R.string.home_screen)
    } else if (route == "cities" || route.startsWith("cities")) {
        stringResource(id = R.string.cities)
    } else if (route == "news" || route.startsWith("articles")) {
        stringResource(id = R.string.news)
    } else if (route == "landmarks" || route.startsWith("landmarks")) {
        stringResource(id = R.string.landmarks)
    } else if (route == "favorites") {
        stringResource(id = R.string.favorite_landmarks)
    } else if (route == "settings") {
        stringResource(id = R.string.settings)
    } else if (route == "map_of_cities") {
        stringResource(id = R.string.map_of_cities)
    } else if (route == "map_of_landmarks") {
        stringResource(id = R.string.map_of_landmarks)
    } else if (route.startsWith("weather")) {
        stringResource(id = R.string.weather)
    } else {
        stringResource(id = R.string.app_name)
    }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val getAllImages = imagesViewModel.getAllImages.collectAsLazyPagingItems()
//    viewModel.loadCities()
//    val landmarks = viewModel.state
    val items = listOf<MenuItem>(
        MenuItem(
            id = "home",
            itemText = stringResource(id = R.string.home_screen),
            contentDescription = stringResource(id = R.string.home_screen_cd),
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = "cities",
            itemText = stringResource(id = R.string.cities),
            contentDescription = stringResource(id = R.string.cities_cd),
            icon = Icons.Default.LocationOn
        ),
        MenuItem(
            id = "landmarks",
            itemText = stringResource(id = R.string.landmarks),
            contentDescription = stringResource(id = R.string.landmarks_cd),
            icon = Icons.Default.LocationOn
        ),
        MenuItem(
            id = "settings",
            itemText = stringResource(id = R.string.settings),
            contentDescription = stringResource(id = R.string.settings_cd),
            icon = Icons.Default.Settings
        ),
        MenuItem(
            id = "favorites",
            itemText = stringResource(id = R.string.favorite_landmarks),
            contentDescription = stringResource(id = R.string.favorite_landmarks_cd),
            icon = Icons.Default.Favorite
        ),
        MenuItem(
            id = "news",
            itemText = stringResource(id = R.string.news),
            contentDescription = stringResource(id = R.string.news_cd),
            icon = Icons.Default.Notifications
        ),
        MenuItem(
            id = "map_of_cities",
            itemText = stringResource(id = R.string.map_of_cities),
            contentDescription = stringResource(id = R.string.map_of_cities_cd),
            icon = Icons.Default.LocationOn
        ),
        MenuItem(
            id = "map_of_landmarks",
            itemText = stringResource(id = R.string.map_of_landmarks),
            contentDescription = stringResource(id = R.string.map_of_landmarks_cd),
            icon = Icons.Default.LocationOn
        )
    )
    ModalNavigationDrawer(
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = items,
                onItemClick = { item -> navController.navigate(route = item.id)})
        },
        drawerState = drawerState,
        drawerShape = RectangleShape
    ) {
        Scaffold(
            topBar = {
                AppBar(title = currentScreenTitle, onNavigationIconClick = { scope.launch { drawerState.open() } })
            }
        )
        { it ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.Yellow)
//                    .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
//            )
//            {
                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        ),
                    navController = navController,
                    startDestination = "home",
                )
                {
                    composable(route = "home") {
                        if(country!=null) {
                            Home(
                                country = country,
                                imagesViewModel = imagesViewModel,
                                openCitiesMap = { navController.navigate(route = "map_of_cities") },
                                openLandmarksMap = { navController.navigate(route = "map_of_landmarks") })
                        }
                    }
                    composable(route = "cities") {
                        if (cities != null) {
                            CityList(
                                cities = cities,
                                modifier = Modifier.fillMaxSize(),
                                onItemClick = { item -> navController.navigate(route = "cities/${item.id}") },
                            onFloatingButtonClick = {navController.navigate(route = "map_of_cities")})
                        }
                    }
                    composable(route = "landmarks") {
                        if(landmarks!=null) {
                            LandmarkList(
                                landmarks = landmarks,
                                modifier = Modifier.fillMaxSize(),
                                onItemClick = { item -> navController.navigate(route = "landmarks/${item.id}") },
                                onFloatingButtonClick = { navController.navigate(route = "map_of_landmarks") })
                        }
                    }
                    composable(route = "news") {
                        News()
                    }
                    composable(route = "settings") {
                        SettingsScreen(settingsViewModel = settingsViewModel)
                    }
                    composable(route = "favorites") {
                        val favoriteLandmarks = guideViewModel.favoriteLandmarks.value
                        if(favoriteLandmarks!=null) {
                            LandmarkList(
                                landmarks = favoriteLandmarks,
                                modifier = Modifier.fillMaxSize(),
                                onItemClick = { item -> navController.navigate(route = "landmarks/${item.id}") },
                                onFloatingButtonClick = { navController.navigate(route = "map_of_landmarks") })
                        }
                    }
                    composable(
                        route = "cities/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType; defaultValue = 1; nullable = false
                        })
                    ) { entry ->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val selectedCity = cities?.first { city -> city.id == id }
                            if (selectedCity != null) {
                                //title = selectedCity.name
                                val searchViewModel = hiltViewModel<SearchViewModel>()
                                searchViewModel.updateSearchQuery(selectedCity.searchTerm)
                                searchViewModel.search()
                                CityDetails(
                                    city = selectedCity,
                                    modifier = Modifier.fillMaxSize(),
                                    openWeather = { id: Int -> navController.navigate(route = "weather/${id}") },
                                    showOnMap = { navController.navigate(route="map_of_cities?latitude=${selectedCity.latitude}&longitude=${selectedCity.longitude}&zoom=10.0")},
                                searchViewModel)
                            }
                        }
                    }
                    composable(
                        route = "landmarks/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType; defaultValue = 1; nullable = false
                        })
                    ) { entry ->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val selectedLandmark = landmarks?.first { landmark -> landmark.id == id }
                            if(selectedLandmark!=null) {
                                val searchViewModel = hiltViewModel<SearchViewModel>()
                                searchViewModel.updateSearchQuery(selectedLandmark.name)
                                searchViewModel.search()
                                LandmarkDetails(
                                    landmark = selectedLandmark,
                                    modifier = Modifier.fillMaxSize(),
                                    showOnMap = { navController.navigate(route="map_of_cities?latitude=${selectedLandmark.latitude}&longitude=${selectedLandmark.longitude}&zoom=10.0")},
                                    searchViewModel = searchViewModel,
                                    onFavoriteButtonClicked = {landmark: Landmark ->
                                        if(!landmark.isFavorite){
                                            landmark.isFavorite = true
                                            guideViewModel.addToFavoriteLandmarks(landmark.id)
                                        }
                                        else{
                                            landmark.isFavorite = false
                                            guideViewModel.removeFromFavoriteLandmarks(landmark.id)
                                        }
                                    }
                                )
                            }

                        }
                    }
                    composable(
                        route = "weather/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType; defaultValue = 1; nullable = false
                        })
                    ) { entry ->
                        val id = entry.arguments?.getInt("id")
                        if (id != null) {
                            val selectedCity = cities?.first { city -> city.id == id }?.let {
                                loadWeather(it)
                                CityWeather(city = it, weatherState)
                            }
                        }
                    }
                    composable(
                        route = "map_of_cities?latitude={latitude}&longitude={longitude}&zoom={zoom}",
                        arguments = listOf(
                            navArgument("latitude"){
                            type = NavType.FloatType; defaultValue = 44.04338f; nullable = false
                        },
                            navArgument("longitude"){
                                type = NavType.FloatType; defaultValue = 17.78456f; nullable = false
                            },
                            navArgument("zoom"){
                                type = NavType.FloatType; defaultValue = 7f; nullable = false
                            }
                        )
                    ) {
                        entry ->
                        val latitude = entry.arguments?.getFloat("latitude")?.toDouble() ?: 44.04338
                        val longitude = entry.arguments?.getFloat("longitude")?.toDouble() ?: 44.04338
                        val zoom = entry.arguments?.getFloat("zoom") ?: 7f
                        CitiesMap(
                            modifier = Modifier.fillMaxSize(),
                            cities,
                            onMarkerClick = { id: Int -> navController.navigate("landmarks/${id}") },
                            latitude = latitude,
                            longitude = longitude,
                            zoom = zoom,
                        )
                    }
                    composable(route = "map_of_landmarks?latitude={latitude}&longitude={longitude}&zoom={zoom}"){
                            entry ->
                        val latitude = entry.arguments?.getFloat("latitude")?.toDouble() ?: 44.04338
                        val longitude = entry.arguments?.getFloat("longitude")?.toDouble() ?: 44.04338
                        val zoom = entry.arguments?.getFloat("zoom") ?: 7f
                        LandmarksMap(landmarks = landmarks, onMarkerClick = { id: Int -> navController.navigate("landmarks/${id}") }, latitude = latitude, longitude = longitude, zoom=zoom )
                    }
                    composable(route = "news") {
//                        newsViewModel.loadNews()
                        if(newsState.news!=null){
                            NewsScreen(
                                news = newsState.news,
                                onHeadlineClick = { headline -> navController.navigate(route = "articles/${headline.id}") })
                        }
                    }
                    composable(
                        route = "articles/{id}",
                        arguments = listOf(navArgument("id") {
                            type = NavType.IntType; nullable = false
                        })
                    ) { entry ->
                        val id = entry.arguments?.getInt("id")
                        if(id!=null){
                            val headline =
                                newsState.news?.articles?.first { article -> article.id == id }
                            if(headline!=null){
                                Article(headline = headline)
                            }
                        }
                    }
                }
//            }
        }

    }
}

private fun getTitleByRoute(route: String) = if (route == "home") {
    "Home"
} else if (route == "cities" || route.startsWith("cities")) {
    "Cities"
} else if (route == "news" || route.startsWith("articles")) {
    "News"
} else if (route == "landmarks" || route.startsWith("landmarks")) {
    "Landmarks"
} else if (route == "favorites") {
    "Favorites"
} else if (route == "settings") {
    "Settings"
} else if (route == "map_of_cities") {
    "Map of cities"
} else if (route == "map_of_landmarks") {
    "Map of landmarks"
} else if (route.startsWith("weather")) {
    "Weather"
} else {
    "Tourist guide"
}

@Composable
fun DrawerHeader() {
    //TODO lokalizacija
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.secondary)) {
        Text(text = "Tourist guide", color = MaterialTheme.colorScheme.onSecondary)
    }

}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { println(item.id); onItemClick(item) }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.itemText, style = itemTextStyle, modifier = Modifier.weight(1f))
            }
        }
    }

}