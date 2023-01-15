package com.mr.touristguide.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.model.City
import java.time.format.TextStyle

@Composable
fun CityList(cities: List<City>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier){
        items(items = cities){
            item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = item.name, style= androidx.compose.ui.text.TextStyle(fontSize = 16.sp))
            }
        }
    }
    
}