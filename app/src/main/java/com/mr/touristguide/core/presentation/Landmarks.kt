package com.mr.touristguide.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.core.model.Landmark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkList(landmarks: List<Landmark>, modifier: Modifier = Modifier, onItemClick: (Landmark) -> Unit, onFloatingButtonClick: () -> Unit) {
    Scaffold(modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {onFloatingButtonClick()},
                shape= CircleShape) {


            }
        }) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding(), bottom=it.calculateBottomPadding())) {
            items(items = landmarks) { item ->
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
fun LandmarkDetails(landmark: Landmark, modifier: Modifier = Modifier) {
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
            Text(text = landmark.name, style=nameStyle)
        }
        Divider(color = Color.Black, thickness = 1.dp)
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        Row(modifier= Modifier.fillMaxWidth()) {
            Text(text=landmark.mainDescription, fontSize=16.sp)
        }
//        Row(modifier = Modifier.fillMaxWidth()){
//            Button(onClick = { openWeather(city.id)}) {
//
//            }
//        }

    }



}