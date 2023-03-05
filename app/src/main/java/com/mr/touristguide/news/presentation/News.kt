package com.mr.touristguide.news.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.mr.touristguide.news.data.remote.HeadlineDto
import com.mr.touristguide.news.data.remote.NewsDto

val titleTextStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
val sourceTextStyle = TextStyle(fontSize = 16.sp)

@Composable
fun NewsScreen(news: NewsDto, onHeadlineClick: (HeadlineDto) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = news.articles) { headline ->
            HeadlinePreview(
                headline = headline,
                modifier = Modifier.fillMaxWidth(),
                onHeadlineClick
            )
        }
    }
}

@Composable
fun HeadlinePreview(
    headline: HeadlineDto,
    modifier: Modifier = Modifier,
    onClick: (HeadlineDto) -> Unit
) {
    Surface(modifier = modifier.shadow(elevation = 5.dp).clickable{onClick(headline)},
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(all = 4.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
//            Column(
//                verticalArrangement = Arrangement.Center,
//                modifier = Modifier.fillMaxWidth()
//            ) {
                Text(text = headline.title, style = titleTextStyle)
                Text(text = headline.source.name, style = sourceTextStyle)
//                if(headline.urlToImage!=null){
//                    Surface(shape= RoundedCornerShape(8.dp)) {
//                        AsyncImage(
//                            model = headline.urlToImage,
//                            contentDescription = null,
//                            contentScale = ContentScale.Crop,
//                            modifier = Modifier.weight(1f),
//                            )
//                    }
//                }
//            }
            if (headline.urlToImage != null) {
                Surface(shape = RoundedCornerShape(8.dp)) {
                    AsyncImage(
                        model = headline.urlToImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.weight(1f)

                    )
                }


            }
        }
    }

}

@Composable
fun Article(modifier: Modifier = Modifier, headline: HeadlineDto) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(all = 4.dp)
    ) {
        Text(text = headline.title, style = titleTextStyle)
        Text(text = headline.source.name, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        if (headline.urlToImage != null) {
            Surface(shape=MaterialTheme.shapes.small) {
                AsyncImage(
                    model = headline.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.weight(1f),

                    )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        if (headline.description != null)
            Text(text = headline.description, fontSize = 19.sp, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.height(5.dp))
        if (headline.content != null)
            Text(text = headline.content, fontSize = 20.sp)
    }

}