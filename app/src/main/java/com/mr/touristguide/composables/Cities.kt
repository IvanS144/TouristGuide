package com.mr.touristguide.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.touristguide.model.City
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import com.mr.touristguide.dao.CityDao

@Composable
fun CityList(cities: List<City>, modifier: Modifier = Modifier, onItemClick: (City) -> Unit) {
    LazyColumn(modifier = modifier){
        items(items = cities){
            item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onItemClick(item) }) {
                Text(text = item.name, style= TextStyle(fontSize = 16.sp))
            }
        }
    }
    
}

@Composable
fun CityDetails(id: Int?, cityDao: CityDao,modifier: Modifier = Modifier) {
    var index = 1;
    if(id != null)
    {
        index = id
    }
    val city = cityDao.getById(index)
    val scrollState = rememberScrollState()
    val nameStyle = TextStyle(fontSize=20.sp, fontStyle = FontStyle.Italic)
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state = scrollState)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = city.name, style=nameStyle)
        }
        Divider(color = Color.Black, thickness = 1.dp)
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        Row(modifier= Modifier.fillMaxWidth()) {
            Text(text=city.mainDescription, fontSize=16.sp)
        }

    }



}