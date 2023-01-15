package com.mr.touristguide.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.Greeting

@Composable
fun Cities() {
    Column(
        modifier = Modifier
                .fillMaxSize()
                .background(Color.Yellow).padding(top = 50.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Cities", style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color.Blue,
                        blurRadius = 3f
                    )
                )
            )
        }
    }
}

@Composable
fun Znamenitosti() {
    Greeting(name = "Znamenitosti")
}

@Composable
fun News() {
    Greeting(name = "News")
}

@Composable
fun Settings() {
    Greeting(name = "Settings")
}

@Composable
fun Home() {
    Greeting(name = "Home")
}

@Composable
fun Favorites() {
    Greeting(name = "Favorites")
}