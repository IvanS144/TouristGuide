package com.mr.touristguide.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.mr.touristguide.R
import com.mr.touristguide.core.model.Country
import com.mr.touristguide.core.presentation.data.SearchViewModel
import com.mr.touristguide.ui.theme.textLargeItalic
import com.mr.touristguide.ui.theme.textNormal
import com.mr.touristguide.ui.theme.textSmall
import com.mr.touristguide.ui.theme.titleLargeItalic

@OptIn(ExperimentalPagingApi::class)
@Composable
fun Home(
    modifier: Modifier = Modifier,
    country: Country,
    searchViewModel: SearchViewModel,
    openCitiesMap: () -> Unit,
    openLandmarksMap: () -> Unit
) {
    val images = searchViewModel.searchedImages.collectAsLazyPagingItems()
//    val nameStyle = TextStyle(fontSize = 40.sp, fontStyle = FontStyle.Italic)
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item{
            val model = if (country.flagUrl.endsWith("svg")) {
                ImageRequest.Builder(LocalContext.current)
                    .data(country.coatOfArmsUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            } else {
                ImageRequest.Builder(LocalContext.current)
                    .data(country.coatOfArmsUrl)
                    .build()

            }
            Spacer(modifier = Modifier.height(12.dp))
            Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
                AsyncImage(
                    model = model,
                    contentDescription = "Flag of" + country.name,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = country.name, style = titleLargeItalic)
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
                        text = country.shortDescription,
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
                Button(onClick = { openCitiesMap() }) {
                    Text(text = stringResource(id = R.string.open_map_of_cities))

                }
                Button(onClick = { openLandmarksMap() }) {
                    Text(text = stringResource(id = R.string.open_map_of_landmarks))
                }
            }
        }
        item{
            val model = if (country.flagUrl.endsWith("svg")) {
                ImageRequest.Builder(LocalContext.current)
                    .data(country.flagUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            } else {
                ImageRequest.Builder(LocalContext.current)
                    .data(country.flagUrl)
                    .build()

            }
            AsyncImage(
                model = model,
                contentDescription = "Flag of" + country.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.border(2.dp, Color.Black)
            )
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(), shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(all = 4.dp)
                ) {
                    for (property in country.properties) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = property.key,
                                style = textSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                            Text(
                                text = property.value,
                                style = textSmall,
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
                        text = country.mainDescription,
                        style = textNormal,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
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