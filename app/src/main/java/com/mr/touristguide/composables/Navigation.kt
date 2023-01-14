@file:OptIn(ExperimentalMaterial3Api::class)

package com.mr.touristguide.composables

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
import androidx.navigation.compose.rememberNavController
import com.mr.touristguide.Greeting
import com.mr.touristguide.composables.data.MenuItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerScreen() {
//    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
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
        )
    )
    ModalNavigationDrawer(drawerContent = {
        DrawerHeader()
        DrawerBody(items)
    },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = {scope.launch{drawerState.open()}})
            }
        )
        {
            Surface(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)){
                Greeting(name = "Tekst")
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
//    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                .clickable { onItemClick(item) }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.itemText, style = itemTextStyle, modifier = Modifier.weight(1f))
            }
        }
    }

}