package com.mr.touristguide.news.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
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
            .padding(horizontal = 4.dp, vertical = 12.dp),
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
    val context = LocalContext.current
    Surface(modifier = modifier
        .clickable { val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(headline.url)
        )
            ContextCompat.startActivity(context, browserIntent, null)},
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(all = 4.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = headline.title, style = titleTextStyle, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = headline.source.name, style = sourceTextStyle, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            if (headline.urlToImage != null) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
        Spacer(modifier = Modifier.height(12.dp))
        if (headline.urlToImage != null) {
            Surface(shape= RoundedCornerShape(8.dp)) {
                AsyncImage(
                    model = headline.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                    )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (headline.description != null)
            Text(text = headline.description, fontSize = 20.sp, fontStyle = FontStyle.Italic)
//        Spacer(modifier = Modifier.height(12.dp))
        if (headline.content != null) {
            Divider(thickness = 2.dp, color = Color.Black)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = headline.content, fontSize = 18.sp)
        }
    }

}