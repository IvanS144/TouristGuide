package com.mr.touristguide.core.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mr.touristguide.R
import com.mr.touristguide.core.data.mappers.sectionsToAnnotatedString
import com.mr.touristguide.core.model.Landmark
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.mr.touristguide.ui.theme.textLargeItalic
import com.mr.touristguide.ui.theme.textNormal
import com.mr.touristguide.ui.theme.titleLargeItalic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandmarkList(
    landmarks: List<Landmark>,
    modifier: Modifier = Modifier,
    onItemClick: (Landmark) -> Unit,
    onFloatingButtonClick: () -> Unit
) {
    Scaffold(modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFloatingButtonClick() },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.map), contentDescription = stringResource(id = R.string.map_of_landmarks_cd))
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            items(items = landmarks) { item ->
                Surface(
                    modifier = Modifier.padding(all = 4.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable { onItemClick(item) },
                    verticalArrangement = Arrangement.spacedBy(6.dp))
                    {
                        item.image?.let { it1 -> UnsplashItem(unsplashImage = it1) }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        horizontalArrangement = Arrangement.Center){
                            if (item.isFavorite) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = stringResource(id = R.string.favorite_landmark_icon_description)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                            }

                        Text(
                            text = item.name,
                            style = textNormal,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
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
fun LandmarkDetails(
    landmark: Landmark,
    modifier: Modifier = Modifier,
    showOnMap: () -> Unit,
    searchViewModel: SearchViewModel,
    onFavoriteButtonClicked: (Landmark) -> Unit
) {
    val images = searchViewModel.searchedImages.collectAsLazyPagingItems()
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
//    val nameStyle = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Italic)
    val context = LocalContext.current
    val favoriteState = rememberSaveable { mutableStateOf(landmark.isFavorite) }
//    val youtubePlayerView = YouTubePlayerView(context)
//    LocalLifecycleOwner.current.lifecycle.addObserver(youtubePlayerView)
//    youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//        override fun onReady(youTubePlayer: YouTubePlayer) {
//            super.onReady(youTubePlayer)
////            youTubePlayer.loadVideo("5g4fhqCSdLQ", 0f)
//            youTubePlayer.cueVideo("5g4fhqCSdLQ", 0f)
//        }
//    })
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = landmark.name, style = titleLargeItalic)
            }
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(all = 4.dp)
                ) {
                    Text(
                        text = landmark.shortDescription,
                        style = textLargeItalic,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { showOnMap() }) {
                    Text(text = stringResource(id = R.string.show_on_map))
                }
                Button(
                    onClick = {
                        onFavoriteButtonClicked(landmark); favoriteState.value = landmark.isFavorite
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = if (favoriteState.value) {
                            MaterialTheme.colorScheme.secondary
                        } else {
                            MaterialTheme.colorScheme.secondaryContainer
                        }
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(id = R.string.favorite_landmark_icon_description),
                        tint = if (favoriteState.value) {
                            MaterialTheme.colorScheme.onSecondary
                        } else {
                            MaterialTheme.colorScheme.onSecondaryContainer
                        }
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
//        TODO main image
//        TODO tabela statistike
        //placeholder text
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp), shadowElevation = 5.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(all = 4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                            .clickable { expanded = !expanded },
                        text = sectionsToAnnotatedString(landmark.sections),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        maxLines = if(expanded) Int.MAX_VALUE else 10,
                        overflow = TextOverflow.Ellipsis
                    )
                    if(!expanded) {
                        Button(onClick = { expanded = !expanded }) {
                            Text(text = stringResource(id = R.string.show_more))

                        }
                    }
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
        items(items = images, key = { unsplashImage -> unsplashImage.id }) { unsplashImage ->
            unsplashImage?.let {
//            Spacer(modifier = Modifier.height(12.dp))
                UnsplashItem(unsplashImage = it)
            }
        }
        item{
            Spacer(modifier = Modifier.height(12.dp))
        }

    }


}