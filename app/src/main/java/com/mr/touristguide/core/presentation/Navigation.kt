@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.touristguide.core.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mr.touristguide.core.presentation.data.MenuItem
import com.mr.touristguide.core.model.City
import com.mr.touristguide.news.presentation.Article
import com.mr.touristguide.news.presentation.NewsScreen
import com.mr.touristguide.news.presentation.NewsState
import com.mr.touristguide.news.presentation.NewsViewModel
import com.mr.touristguide.weather.presentation.WeatherState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerScreen(cities: List<City>?, weatherState: WeatherState, newsState: NewsState, loadWeather: (City) -> Unit) {
//    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    viewModel.loadCities()
//    val cities = viewModel.state
    val items = listOf<MenuItem>(
        MenuItem(
            id = "home",
            itemText = "Home",
            contentDescription = "Go to home screen",
            icon = Icons.Default.Home
        ),
        MenuItem(
            id = "cities",
            itemText = "Cities",
            contentDescription = "Go to cities screen",
            icon = Icons.Default.LocationOn
        ),
        MenuItem(
            id = "znamenitosti",
            itemText = "Znamenitosti",
            contentDescription = "Go to znamenitosti screen",
            icon = Icons.Default.LocationOn
        ),
        MenuItem(
            id = "settings",
            itemText = "Settings",
            contentDescription = "Go to settings",
            icon = Icons.Default.Settings
        ),
        MenuItem(
            id = "favorites",
            itemText = "Favorite locations",
            contentDescription = "Go to favorite locations screen",
            icon = Icons.Default.Favorite
        ),
        MenuItem(
            id = "news",
            itemText = "News",
            contentDescription = "Go to news screen",
            icon = Icons.Default.Notifications
        ),
        MenuItem(
            id = "citiesmap",
            itemText = "Map of cities",
            contentDescription = "Go to map of cities",
            icon = Icons.Default.LocationOn
        )
    )
    ModalNavigationDrawer(drawerContent = {
        DrawerHeader()
        DrawerBody(items = items, onItemClick = {item  -> navController.navigate(route=item.id) } )
    },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = {scope.launch{drawerState.open()}})
            }
        )
        {it ->
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding()))
            {
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = "home",
                )
                {
                    composable(route = "home") {
                        Home()
                    }
                    composable(route = "cities") {
                        if(cities!=null) {
                            CityList(
                                cities = cities,
                                modifier = Modifier.fillMaxSize(),
                                onItemClick = { item -> navController.navigate(route = "cities/${item.id}") })
                        }
                    }
                    composable(route = "znamenitosti") {
                        Znamenitosti()
                    }
                    composable(route = "news") {
                        News()
                    }
                    composable(route = "settings") {
                        Settings()
                    }
                    composable(route = "favorites") {
                        Favorites()
                    }
                    composable(route = "cities/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue=1; nullable = false })){
                        entry ->
                        val id = entry.arguments?.getInt("id")
                        if(id!=null) {
                            val selectedCity = cities?.first{city -> city.id ==id}?.let {
                                CityDetails(
                                    city = it,
                                    modifier = Modifier.fillMaxSize(),
                                    openWeather = { id: Int -> navController.navigate(route = "weather/${id}") })
                            }
                        }
                    }
                    composable(route="weather/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue=1; nullable = false })){
                        entry ->
                        val id = entry.arguments?.getInt("id")
                        if(id!=null) {
                            val selectedCity = cities?.first{city -> city.id==id}?.let {
                                loadWeather(it)
                                CityWeather(city = it, weatherState)
                            }
                        }
                    }
                    composable(route = "citiesmap"){
                        CitiesMap(modifier = Modifier.fillMaxSize(), cities, onMarkerClick = {id: Int -> navController.navigate("cities/${id}")})
                    }
                    composable(route="news"){
//                        newsViewModel.loadNews()
                        newsState.news?.let {news ->
                            NewsScreen(news = news, onHeadlineClick = {headline -> navController.navigate(route="articles/${headline.id}")} )
                        }
                    }
                    composable(route="articles/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType; nullable = false })){
                        entry ->
                        val id = entry.arguments?.getInt("id")
                        println("KISAMAAAAAAAAAAAAAAAAAAAAA $id")
                        println(newsState.news?.articles)
                        id?.let{
                                val headline = newsState.news?.articles?.first{article -> article.id ==id}
                                headline?.let {
                                    Article(headline = headline)
                                }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun DrawerHeader() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Tourist guide")
    }

}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
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