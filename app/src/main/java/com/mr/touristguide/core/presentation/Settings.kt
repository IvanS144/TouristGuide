package com.mr.touristguide.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mr.touristguide.core.presentation.data.SettingsViewModel
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel
) {
    val maxImagesValues = listOf(10, 20, 50, 100 )
    Column(modifier = Modifier.fillMaxSize().padding(all=5.dp)) {
//        Surface(modifier = Modifier.padding(all=4.dp), shape = MaterialTheme.shapes.small) {
//            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.background(Color.LightGray)) {
                Row(){
                    Text("How many images do you want to be loaded when exploring cities and landmarks?")
                }
                for (value in maxImagesValues) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = value == settingsViewModel.maxImagesState,
                            onClick = { settingsViewModel.setMaxImages(value) })
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = value.toString())
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = -1 == settingsViewModel.maxImagesState,
                        onClick = { settingsViewModel.setMaxImages(-1) })
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Unlimited")
                }
//            }
//        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Turn on caching for news")
            Switch(checked = settingsViewModel.newsCachingState, onCheckedChange = {settingsViewModel.setNewsCaching(!settingsViewModel.newsCachingState)})
        }
    }
}