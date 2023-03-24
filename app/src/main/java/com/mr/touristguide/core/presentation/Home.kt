package com.mr.touristguide.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mr.touristguide.core.model.Country
import com.mr.touristguide.core.presentation.data.ImagesViewModel
import com.mr.touristguide.R
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@OptIn(ExperimentalPagingApi::class)
@Composable
fun Home(modifier: Modifier = Modifier, country: Country, searchViewModel: SearchViewModel, openCitiesMap: () -> Unit, openLandmarksMap: ()-> Unit) {
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
                Text(text = country.name, style = nameStyle)
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
                .fillMaxWidth(), shape= RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(all = 4.dp)){
                    Text(
                        text = country.shortDescription,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { openCitiesMap() }) {
                    Text(text = stringResource(id = R.string.open_map_of_cities))

                }
                Button(onClick = { openLandmarksMap() }) {
                    Text(text = stringResource(id = R.string.open_map_of_landmarks))
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
        item{
            Surface(modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .padding(all = 4.dp)) {
                    for(property in country.properties) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = property.key,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Text(
                                text = property.value,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }
        }
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        item {
            Surface(modifier = Modifier
                .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(all = 4.dp)) {
                    Text(text = country.mainDescription, fontSize = 16.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
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