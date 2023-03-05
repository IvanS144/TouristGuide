package com.mr.touristguide.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

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

//@Composable
//fun LandmarkDetails(landmark: Landmark, modifier: Modifier = Modifier) {
////    var index = 1;
////    if(id != null)
////    {
////        index = id
////    }
////    val city = cityDao.getById(index)
//    val scrollState = rememberScrollState()
//    val nameStyle = TextStyle(fontSize=40.sp, fontStyle = FontStyle.Italic)
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .verticalScroll(state = scrollState)) {
//        Row(modifier = Modifier.fillMaxWidth()) {
//            Text(text = landmark.name, style=nameStyle)
//        }
//        Divider(color = Color.Black, thickness = 1.dp)
////        TODO main image
////        TODO tabela statistike
//        //placeholder text
//        Row(modifier= Modifier.fillMaxWidth()) {
//            Text(text=landmark.mainDescription, fontSize=16.sp)
//        }
////        Row(modifier = Modifier.fillMaxWidth()){
////            Button(onClick = { openWeather(city.id)}) {
////
////            }
////        }
//
//    }

@OptIn(ExperimentalPagingApi::class)
@Composable
fun LandmarkDetails(landmark: Landmark, modifier: Modifier = Modifier, searchViewModel: SearchViewModel) {
    val images = searchViewModel.searchedImages.collectAsLazyPagingItems()
    val nameStyle = TextStyle(fontSize=40.sp, fontStyle = FontStyle.Italic)
    val context = LocalContext.current
//    val youtubePlayerView = YouTubePlayerView(context)
//    LocalLifecycleOwner.current.lifecycle.addObserver(youtubePlayerView)
//    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//        override fun onReady(youTubePlayer: YouTubePlayer) {
//            super.onReady(youTubePlayer)
////            youTubePlayer.loadVideo("5g4fhqCSdLQ", 0f)
//            youTubePlayer.cueVideo("5g4fhqCSdLQ", 0f)
//        }
//    })
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = landmark.name, style = nameStyle)
            }
        }
//        item {
//            Divider(color = Color.Black, thickness = 1.dp)
//        }
//        item{
//            Spacer(modifier = Modifier.height(5.dp))
//        }
        item{
            Surface(modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp), shape= RoundedCornerShape(4.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(all = 4.dp)){
                    Text(
                        text = landmark.shortDescription,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
//        item{
//            Spacer(modifier = Modifier
//                .height(10.dp)
//                .background(Color.Transparent))
//        }
//        item{
//            Surface(modifier = Modifier.shadow(elevation = 5.dp), shape = RoundedCornerShape(8.dp)) {
//                AndroidView(factory = {
////                var view = YouTubePlayerView(it)
////                LocalLifecycleOwner.current.lifecycle.addObserver(view)
////                val fragment = view.addYouTubePlayerListener(
////                    object : AbstractYouTubePlayerListener() {
////                        override fun onReady(youTubePlayer: YouTubePlayer) {
////                            super.onReady(youTubePlayer)
////                            youTubePlayer.loadVideo("5g4fhqCSdLQ", 0f)
////                        }
////                    }
////                )
////                view
//                    youtubePlayerView
//                })
//            }
//        }
//        item{
//            Spacer(modifier= Modifier.height(5.dp))
//        }
//        item{
//            Surface(modifier = Modifier
//                .fillMaxWidth()
//                .shadow(elevation = 5.dp), shape = RoundedCornerShape(4.dp)
//            ) {
//                Column(modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Cyan)
//                    .padding(all = 4.dp)) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "property1")
//                        Text(text = "value1")
//                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "property1")
//                        Text(text = "value1")
//                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "property1")
//                        Text(text = "value1")
//                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "property1")
//                        Text(text = "value1")
//                    }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "property1")
//                        Text(text = "value1")
//                    }
//                }
//            }
//        }
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        item {
            Surface(modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp), shape = RoundedCornerShape(8.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(all = 4.dp)) {
                    Text(text = landmark.mainDescription, fontSize = 16.sp)
                }
            }
        }
//        item {
//            Row(modifier = Modifier.fillMaxWidth()) {
//                Button(onClick = { openWeather(landmark.id) }) {
//
//                }
//            }
//        }
//        item{
//            Spacer(modifier = Modifier.height(5.dp))
//        }
//        items(items = images, key = {unsplashImage -> unsplashImage.id}){
//                unsplashImage -> unsplashImage?.let{ UnsplashItem(unsplashImage = it)}
//        }
//        item{
//            LazyRow(
//                modifier = Modifier.fillMaxWidth().height(500.dp).background(Color.Black),
//                contentPadding = PaddingValues(all = 12.dp),
//                horizontalArrangement = Arrangement.spacedBy(12.dp)){
//                items(items = images, key = {unsplashImage -> unsplashImage.id}){
//                        unsplashImage -> unsplashImage?.let{ UnsplashItem(unsplashImage = it)}
//                }
//            }
//        }
        items(items = images, key = {unsplashImage -> unsplashImage.id}){
                unsplashImage -> unsplashImage?.let{
//            Spacer(modifier = Modifier.height(12.dp))
            UnsplashItem(unsplashImage = it)}
        }

    }



}