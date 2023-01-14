package com.mr.touristguide.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    SmallTopAppBar(
        title = {
            Text(text = "App bar text")
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle drawer" )
            }
        }
    )
}